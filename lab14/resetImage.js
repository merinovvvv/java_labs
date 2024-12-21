document.querySelector("form").addEventListener("reset", function () { //Находит первый элемент <form> на странице и добавляет ему обработчик события reset. Если на странице несколько форм, выбирается только первая.
    const fileInput = document.getElementById("photo"); //Находит элемент с id="photo", который является полем <input type="file">. Поле выбора файла хранит информацию о выбранном пользователем файле. В данном случае нужно сбросить его значение
    const imagePreview = document.getElementById("imagePreview"); //Находит элемент с id="imagePreview". Это <img>, используемое для отображения предварительного просмотра выбранного изображения. Чтобы очистить и скрыть предварительный просмотр изображения, когда форма сбрасывается
    
    // Сброс значения input type="file" (Очищает значение поля выбора файла)
    fileInput.value = "";

    // Скрыть предварительный просмотр изображения
    imagePreview.style.display = "none";
    imagePreview.src = "";
});
