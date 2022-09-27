$(document).ready(function ($) {
    console.log("test1");
    let pathHost = location.hostname;
    let path = location.pathname;
    let postNo = path.split('/').slice(-1)[0];

    if (path == "/") {
        console.log("index");
        readBoards();
    } else if(path.includes("/view/post")) {
        console.log("detail");
        detailPost(postNo);

    } else if (path.includes("/create")) {
        console.log("create");
    }
     else {
        console.log("else");
    }

    $(function() {
        $(`#table-row`).click(function() {
            console.log("click3");
        });
    })

})


readBoards = function(){
    $.ajax({
        type: `GET`,
        url: `/api/posts`,
        success: function(response) {
            $('#postTable').empty();

            for (let i=0; i < response.length; i++) {
                let itemDto = response[i];
                let tempHtml = addHTMLTable(itemDto);
                $("#postTable").append(tempHtml);
            }
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

function addHTMLTable(itemDto) {
    // window.location.replace(`/view/post/${targetNo}`);

    return `<tr onClick=window.location.replace("/view/post/${itemDto.postNo}")>
        <td class="px-4">${itemDto.title}</td>
        <td class="text-right px-4">${splitDate(itemDto.createdAt)}</td>
        <td class="text-right px-4">${itemDto.viewCount}</td>
    </tr>`

}

function splitDate(dateTime){
    let createDate = dateTime.toString().split('T');
    return createDate[0];
}

function splitTime(dateTime) {
    let createDate = splitDate(dateTime);
    let allTime = dateTime.toString().split('T')[1].split(':');
    let createTime = allTime[0] + ':' + allTime[1];

    return createDate + ' ' + createTime;
}

detailPost = function(postNo) {

    $.ajax({
        type: `GET`,
        url: `/api/post/${postNo}`,
        success: function(response) {
            $("#detailPost").empty();
            console.log(response);
            let tempHtml = addPost(response);
            $("#detailPost").append(tempHtml);
            let tempBtn = addBtn(postNo);
            $("#updateBtn").append(tempBtn);
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

function addPost(itemDto) {
    return `<h1>${itemDto.title}</h1>
    <p>${splitTime(itemDto.createdAt)}</p>
    <hr>
    <div>${itemDto.content}</div>
    `
}

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

function addBtn(postNo) {
    return `
    <button type="button" class="btn btn-outline-danger" onClick="deletePost(${postNo});">삭제</button>
    <button type="button" class="btn btn-outline-secondary" onClick="location.href='/';">목록</button>
    <button type="button" class="btn btn-outline-primary" onClick="window.location.replace('/view/update/post/${postNo}');">수정</button>`
}