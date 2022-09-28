$(document).ready(function () {
    console.log("summernote");
    let path = location.pathname;
    let targetNo;
    let postNo = path.split('/').slice(-1)[0];
    
    if (path.includes("/update")){
        updatePost(postNo);
    }

    $('#summernote').summernote({
        height: 300,
        minHeight: null,
        maxHeight: null,
        focus: true,
        toolbar: [
            // [groupName, [list of button]]
            ['fontname', ['fontname']],
            ['fontsize', ['fontsize']],
            ['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
            ['color', ['forecolor','color']],
            ['table', ['table']],
            ['para', ['ul', 'ol', 'paragraph']],
            ['height', ['height']],
            ['insert',['picture','link','video']],
            ['view', ['fullscreen', 'help']]
          ],
        fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋움체','바탕체'],
        fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72'],
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
    let title = $("#title");
    let content = $("#summernote");
    let data = JSON.stringify({ "title": title.val(), "content": content.val() });
    console.log(data);

    let path = location.pathname;
    
    if (path.includes("/update")) {
        let postNo = path.split('/').slice(-1)[0];

        $.ajax({
            type: "PUT",
            url: `/api/post/${postNo}`,
            contentType: "application/json",
            data: data,
            success: function (response) {
                console.log(response);
    
                let targetNo = response['postNo'];
                console.log(targetNo);
                window.location.replace(`/view/post/${targetNo}`);
            },
            error: function (response) {
                if (response.responseJSON && response.responseJSON.message) {
                    alert(response.responseJSON.message);
                } else {
                    alert("알 수 없는 에러가 발생하였습니다.");
                }
            }
        })
    } else {
        $.ajax({
            type: "POST",
            url: `/api/post`,
            contentType: "application/json",
            data: data,
            success: function (response) {
                console.log(response);
    
                let targetNo = response['postNo'];
                console.log(targetNo);
                window.location.replace(`/view/post/${targetNo}`);
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

    
}

updatePost = function(postNo) {
    let title = $("#title");
    let content = $("#summernote");

    $.ajax({
        type: "GET",
        url: `/api/post/${postNo}`,
        // contentType: "application/json",
        // data: data,
        success: function (response) {
            console.log(response);
            title.val(response.title);
            content.summernote('code', response.content);
            // content.summernote('insertText', response.content);
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