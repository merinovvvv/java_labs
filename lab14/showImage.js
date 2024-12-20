function previewImage(event) {
    const file = event.target.files[0]; //event.target — объект, инициировавший событие (<input type="file">).
    if (file) {
        const reader = new FileReader();
        reader.onload = function(e) { //событие, которое срабатывает, когда чтение файла завершено
            const imagePreview = document.getElementById('imagePreview'); //находит элемент <img> с идентификатором imagePreview и сохраняет
            imagePreview.src = e.target.result; //e.target.result — результат чтения файла
            imagePreview.style.display = 'block'; //делает элемент <img> видимым
        };
        reader.readAsDataURL(file); //читает содержимое файла и преобразует его в строку, это позволяет использовать файл как источник данных для изображения
    } else {
        document.getElementById('imagePreview').style.display = 'none'; //скрывается элемент предварительного просмотра, устанавливая стиль display: none.
    }
}
