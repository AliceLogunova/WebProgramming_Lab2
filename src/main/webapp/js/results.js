function redirectToMain() {
    window.location.href = "./index.jsp";
}

$(document).ready(function () {
    let result = sessionStorage.getItem(sessionStorage.length - 1);

    if (result) {
        result = JSON.parse(result);

        const result_content = `<div id="param_tab">
                                x:${result.x || ''}
                                <br>
                                y:${result.y || ''}
                                <br>
                                R:${result.R || ''}
                                <br>
                                collision:${result.collision || ''}
                                <br>
                                execution time:${result.executionTime || ''}
                                <br>
                                time:${result.time || ''}
                                <br>`;

        $("#result_div").html(result_content);
    }
});
