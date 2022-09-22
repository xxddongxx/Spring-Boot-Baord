$(document).ready(function () {
    console.log("test1");
    readBoard();
})


readBoard = function(){
    $.ajax({
        type: `GET`,
        url: `/api/posts`,
        success: function(response) {
            $('#postTable').empty();

            for (let i=0; i < response.length; i++) {
                let itemDto = response[i];
                console.log(itemDto);
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
    return `<tr>
        <td class="px-4">${itemDto.title}</td>
        <td class="text-right px-4">${splitTime(itemDto.createdAt)}</td>
        <td class="text-right px-4">${itemDto.viewCount}</td>
    </tr>`

}

function splitTime(time){
    let splitTime = time.toString().split('T');
    return splitTime[0];
}