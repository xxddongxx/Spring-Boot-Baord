function deletePost(postNo) {
    $.ajax({
        type: `PUT`,
        url: `/api/post/delete/${postNo}`,
        success: function(response) {
            window.location.href='/';
        },
        error: function(response) {
            if (response.responseJSON && response.responseJSON.message) {
                alert(response.responseJSON.message);
            } else {
                alert("알 수 없는 에러가 발생하였습니다.");
            }
        }
    })
}

function detailPost(postNo) {
    location.href=`/view/post/${postNo}`;
}

function listPost() {
    location.href=`/`;
}

function updatePost(postNo) {
    location.href=`/view/update/post/${postNo}`;
}
