package controller;

import model.Set;
import view.MyApplication;
import visitor.DifferenceVisitor;
import visitor.IntersectionVisitor;
import visitor.UnionVisitor;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SetController {
    private final Set<String> setA;
    private final Set<String> setB;
    private final MyApplication view;

    public SetController(Set<String> setA, Set<String> setB, MyApplication view) {
        this.setA = setA;
        this.setB = setB;
        this.view = view;
        initController();
    }

    private void initController() {
        this.view.getAddButton().addActionListener(_ -> addElementsToSetAndList());

        this.view.getClearButton().addActionListener(_ -> clearSetAndList());

        this.view.getRemoveButton().addActionListener(_ -> removeSelectedElementsFromLists());

        this.view.getUniteButton().addActionListener(_ -> {
            UnionVisitor<String> unionVisitor = new UnionVisitor<>(setA);
            setB.accept(unionVisitor);
            this.view.getResultTextField().setText(unionVisitor.getResult().toString());
        });

        this.view.getIntersectButton().addActionListener(_ -> {
            IntersectionVisitor<String> intersectionVisitor = new IntersectionVisitor<>(setA);
            setB.accept(intersectionVisitor);
            this.view.getResultTextField().setText(intersectionVisitor.getResult().toString());
        });

        this.view.getDifferenceABButton().addActionListener(_ -> {
            DifferenceVisitor<String> differenceVisitor = new DifferenceVisitor<>(setA);
            setB.accept(differenceVisitor);
            this.view.getResultTextField().setText(differenceVisitor.getResult().toString());
        });

        this.view.getElemToAddTextField().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                toggleButtonState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                toggleButtonState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                toggleButtonState();
            }

            private void toggleButtonState() {
                boolean isTextFieldEmpty = view.getElemToAddTextField().getText().trim().isEmpty();
                view.getAddButton().setEnabled(!isTextFieldEmpty);
            }
        });

    }

    private void addElementsToSetAndList() {
        StringTokenizer st = new StringTokenizer(this.view.getElemToAddTextField().getText());
        List<String> tokens = new ArrayList<>();
        while (st.hasMoreTokens()) {
            tokens.add(st.nextToken());
        }
        if (this.view.isSetASelected()) {
            setA.addAll(tokens);
            this.view.updateSetDisplay(setA);
        } else {
            setB.addAll(tokens);
            this.view.updateSetDisplay(setB);
        }
        this.view.getClearButton().setEnabled(true);
        updateButtonStates();
    }

    private void clearSetAndList() {
        if (this.view.isSetASelected()) {
            setA.clear();
            this.view.updateSetDisplay(setA);
        } else {
            setB.clear();
            this.view.updateSetDisplay(setB);
        }

        updateButtonStates();
    }

    private void removeSelectedElementsFromLists() {
        List<String> selectedValuesA = setA.toJList().getSelectedValuesList(); //TODO bug
        List<String> selectedValuesB = setB.toJList().getSelectedValuesList(); //TODO bug

        for (String value : selectedValuesA) {
            setA.remove(value);
        }
        for (String value : selectedValuesB) {
            setB.remove(value);
        }

        this.view.updateSetDisplay(setA);
        this.view.updateSetDisplay(setB);

        updateButtonStates();
    }

    private void updateButtonStates() {
        boolean isSetAEmpty = setA.isEmpty();
        boolean isSetBEmpty = setB.isEmpty();
        this.view.getClearButton().setEnabled(!isSetAEmpty || !isSetBEmpty);
        this.view.getRemoveButton().setEnabled(!isSetAEmpty || !isSetBEmpty);
    }
}
