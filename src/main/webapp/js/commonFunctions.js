
function sendRequest(url, successCallback) {
    const xhr = new XMLHttpRequest();
    xhr.open("POST", url, true);

    xhr.onreadystatechange = () => {
        if (xhr.readyState === 4 && xhr.status === 200) {
            successCallback(xhr.responseText);
        }
    };

    xhr.send();
}


function appendResultToTable(result) {
    const resultsElement = document.getElementById("param_tab").querySelector("tbody");
    const newRow = document.createElement("tr");
    newRow.innerHTML = result;
    resultsElement.appendChild(newRow);
}