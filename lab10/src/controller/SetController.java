package controller;

import model.SetModel;
import view.View;
import visitor.DifferenceVisitor;
import visitor.IntersectionVisitor;
import visitor.UnionVisitor;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SetController {
    private final SetModel<String> setA;
    private final SetModel<String> setB;
    private final View view;

    public SetController(SetModel<String> setA, SetModel<String> setB, View view) {
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

        this.view.getListA().addListSelectionListener(_ -> updateRemoveButtonState());

        this.view.getListB().addListSelectionListener(_ -> updateRemoveButtonState());

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
        updateClearButtonState();
    }

    private void clearSetAndList() {
        if (this.view.isSetASelected()) {
            setA.clear();
            this.view.updateSetDisplay(setA);
        } else {
            setB.clear();
            this.view.updateSetDisplay(setB);
        }

        updateClearButtonState();
    }

    private void removeSelectedElementsFromLists() {
        List<String> selectedValuesA = this.view.getListA().getSelectedValuesList();
        List<String> selectedValuesB = this.view.getListB().getSelectedValuesList();

        for (String value : selectedValuesA) {
            setA.remove(value);
        }
        for (String value : selectedValuesB) {
            setB.remove(value);
        }

        this.view.updateSetDisplay(setA);
        this.view.updateSetDisplay(setB);

        updateClearButtonState();
    }

    private void updateClearButtonState() {
        this.view.getClearButton().setEnabled(!setA.isEmpty() || !setB.isEmpty());
    }

    private void updateRemoveButtonState() {
        boolean isAnyFieldSelected = !this.view.getListA().isSelectionEmpty() || !this.view.getListB().isSelectionEmpty();
        this.view.getRemoveButton().setEnabled(isAnyFieldSelected);
    }
}
