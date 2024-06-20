function goUpdate() {
    if (!confirm('게시글을 수정하시겠습니까?')) {
        return; // 사용자가 취소를 선택한 경우 아무 것도 하지 않습니다.
    }

    var boardId = document.querySelector('input[name="boardId"]').value;
    window.location.href = '/board/edit/' + boardId;
}

function goDelete() {
    if (confirm('게시글을 삭제하시겠습니까?')) {
        // 사용자가 '확인'을 선택했을 경우, 삭제 절차 진행
        var boardId = document.querySelector('input[name="boardId"]').value;
        // Form을 통해 POST 요청으로 서버에 삭제를 요청하도록 변경
        var form = document.createElement('form');
        form.method = 'post';
        form.action = '/board/delete/' + boardId;
        document.body.appendChild(form);
        form.submit();
    }
    // 사용자가 '취소'를 선택한 경우, 함수를 종료하고 아무것도 하지 않습니다.
}