window.onload = function () {
    document.getElementById("del").onclick = function () {
        window.confirm("确认删除吗?");
    }
    document.getElementById("sub").onclick = function () {
        window.history.back();
    }
}