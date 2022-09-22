$(document).ready(function () {
    console.log("create");

    $('#summernote').summernote({
        height: 300,
        minHeight: null,
        maxHeight: null,
        focus: true,
        callbacks: {
            onImageUpload: function (files, editor, welEditable) {
                for (var i = 0; i < files.length; i++) {
                    sendFile(files[i], this);
                }
            }
        }
    });
});

function sendFile(file, el) {
    var form_data = new FormData();
    form_data.append('file', file);
    $.ajax({
        data: form_data,
        type: "POST",
        url: '/image',
        cache: false,
        contentType: false,
        enctype: 'multipart/form-data',
        processData: false,
        success: function (url) {
            $(el).summernote('insertImage', url, function ($image) {
                $image.css('width', "50%");
            });
        }
    });
}

savePost = function () {
    let title = $("#title").val();
    let content = $("#summernote").val();

    let data = JSON.stringify({ "title": title, "content": content });
    console.log(data);

    $.ajax({
        type: "POST",
        url: `/api/post`,
        contentType: "application/json",
        data: data,
        success: function (response) {
            alert("저장에 성공");
            window.location.replace("http://localhost:8080");
        },
        error: function (response) {
            if (response.responseJSON && response.responseJSON.message) {
                alert(response.responseJSON.message);
            } else {
                alert("알 수 없는 에러가 발생하였습니다.");
            }
        }
    })
}