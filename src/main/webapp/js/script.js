/**
 * fetchResults sends an HTTP request to the server to perform point verification.
 * It processes the server's response and updates the results table.
 */
function fetchResults() {
    if (validate()) {
        const x = document.getElementsByName("coo_x")[0].value;
        const y = document.getElementsByName("coo_y")[0].value;
        const r = document.getElementsByName("radius")[0].value;
        const userLocalDateTime = new Date().toLocaleString();
        const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
        const url = `${contextPath}/checkArea?x=${x}&y=${y}&r=${r}&userLocalDateTime=${userLocalDateTime}`;

        // Use the shared function to send the request
        sendRequest(url, (response) => {
            // Use the shared function to append the result to the table
            appendResultToTable(response);
        });
    }
}

/**
 * clearTable clears the displayed results table and removes results from Local Storage.
 */
function clearTable() {
    const resultsElement = document.getElementById("param_tab").querySelector("tbody");
    resultsElement.innerHTML = "";

    // Send a request to clear the session-stored results
    const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
    const url = `${contextPath}/clearHistory`;

    // Use the shared function to send the request
    sendRequest(url, (response) => {
        // Display a confirmation message
        showNotification(response);
    });
}

/**
 * Load results from Local Storage and append them to the table on page load.
 */
document.addEventListener("DOMContentLoaded", () => {
    fetchServerResults();
});

function fetchServerResults() {
    const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
    const url = `${contextPath}/loadHistory`;

    // Use the shared function to send the request
    sendRequest(url, (response) => {
        // Use the shared function to append the result to the table
        appendResultToTable(response);
    });
}





// let param_table_element_id = 1;
// let param_table = $("#param_tab");
// let error_div = $("#error");
// let R_selector = $("#radius");
//
// function areaCheckGetRequest(data, redirectToResult) {
//     $.ajax({
//         type: "POST",
//         url: "checkArea",
//         async: false,
//         contentType: 'json/application',
//         data: data,
//         success: function (data) {
//             console.log(
//                 `response.x: ${data.x}, response.y: ${data.y}, response.R: ${data.R},\n` +
//                 `response.collision: ${data.collision}, response.execTime: ${data.executionTime}, response.time: ${data.time}`
//             );
//             if (redirectToResult) {
//                 window.location.href = "./result.jsp";
//             } else {
//                 console.log("SuCcEsSfUl ReQuEsT");
//                 const content = `<tr>
//                      <td>${data.x}</td>
//                      <td>${data.y}</td>
//                      <td>${data.R}</td>
//                      <td>${data.collision}</td>
//                      <td>${data.executionTime}s</td>
//                      <td>${data.time}</td>
//                      </tr>`;
//                 param_table.append(content);
//                 param_table_element_id += 1;
//             }
//         },
//         error: function (xhr, textStatus, error) {
//             console.log(
//                 "readyState: " +
//                 xhr.readyState +
//                 "\n" +
//                 "responseText: " +
//                 xhr.responseText +
//                 "\n" +
//                 "status: " +
//                 xhr.status +
//                 "\n" +
//                 "text status: " +
//                 textStatus +
//                 "\n" +
//                 "error: " +
//                 error
//             );
//         },
//     });
// }
//
// function loadHistory() {
//     $.ajax({
//         url: "loadHistory",
//         type: "POST",
//         dataType: "json",
//         success: function (responseData) {
//             $.each(responseData, function (responseData) {
//                 console.log(responseData);
//                 const content = `<tr>
//                      <td>${responseData.x}</td>
//                      <td>${responseData.y}</td>
//                      <td>${responseData.R}</td>
//                      <td>${responseData.collision}</td>
//                      <td>${responseData.executionTime}s</td>
//                      <td>${responseData.time}</td>
//                      </tr>`;
//                 console.log(sessionStorage);
//                 $("#param_tab").append(content);
//             });
//         },
//         error: function (xhr, status, error) {
//             console.error("Error: " + error);
//         },
//     });
// }
//
// function validate(x, y, R) {
//     if (-3 > x || x > 5) {
//         console.log("X");
//         return false;
//     }
//     if (-3 > y || y > 5) {
//         console.log("Y");
//         return false;
//     }
//     if (1 > R || R > 3) {
//         console.log("R");
//         return false;
//     }
//     return true;
// }
//
//
// function showError(msg, delay) {
//     error_div.innerText = msg;
//
//     setTimeout(function () {
//         error_div.innerText = "";
//     }, delay);
// }
//
//
// $(document).ready(function () {
//     loadHistory();
//     $("#clear_but").click(function () {
//         sessionStorage.clear();
//         const content = `<th>Координата X</th>
//                     <th>Координата Y</th>
//                     <th>Величина радиуса</th>
//                     <th>Попадание в область</th>
//                     <th>Время работы php-скрипта</th>
//                     <th>Текущее время</th>;`
//         $("#param_tab").html(content);
//     });
//
//     $("#radius").click(function () {
//         R_selector = $(this).val();
//     });
//
//
//     $("#form").submit(function (event) {
//
//         console.log("aboba4");
//
//         event.preventDefault();
//         const x = $("input[name='coo_x']").val();
//         const y = $("input[name='coo_y']").val();
//         const R = R_selector;
//
//         console.log(x, y, R);
//
//         if (validate(x, y, R)) {
//             areaCheckGetRequest({x: x, y: y, R: R}, true);
//         } else {
//             showError("Что-то не так( Проверьте входят ли значения в промежутки!\nx ∈ [-3; 5]\ny ∈ [-3; 5]\nR ∈ [1; 3]",
//                 5000);
//         }
//     });
//
//
//     $("svg.pic").on("click", function (event) {
//         let clicked_points = [];
//
//         console.log("aboba2");
//
//         if (R_selector) {
//             const svgElement = $(this);
//             const svgRect = svgElement[0].getBoundingClientRect();
//
//             const offsetX = event.clientX - svgRect.left;
//             const offsetY = event.clientY - svgRect.top;
//
//             const width = svgElement.width();
//             const height = svgElement.height();
//
//             console.log(`Ширина: ${width}, Высота: ${height}`);
//             const x = event.offsetX;
//             const y = event.offsetY;
//
//             const R = R_selector.val();
//
//             const point = document.createElementNS("http://www.w3.org/2000/svg", "circle");
//             point.setAttribute("cx", offsetX.toString());
//             point.setAttribute("cy", offsetY.toString());
//             point.setAttribute("r", "3");
//             point.setAttribute("fill", "red");
//
//             svgElement.append(point);
//
//             let centerX = width / 2;
//             let centerY = height / 2;
//             let radius = width / 2 - 50;
//
//             console.log("aboba");
//
//             let normalizedX = (((x - centerX) * 2 * radius) / width).toFixed(2);
//             let normalizedY = (((centerY - y) * 2 * radius) / height).toFixed(2);
//
//             console.log(`x: ${x}, normX: ${normalizedX}`);
//             console.log(`y: ${y}, normY: ${normalizedY}`);
//             console.log(clicked_points[clicked_points.length - 1]);
//
//             areaCheckGetRequest({ x: normalizedX, y: normalizedY, R: radius }, true);
//         } else {
//             showError("Укажите радинус R!", 5000);
//         }
//     });
// });