
function validate() {
    let isValid = true;

    const rSelect = document.getElementsByName("radius")[0].value;
    if (!rSelect.value) {
        rSelect.value = 1;
    }

    const xText = document.getElementsByName("coo_x")[0].value;
    if (!/^[+-]?([0-9]*[.])?[0-9]+$/.test(xText) || !isFinite(parseFloat(xText)) || parseFloat(xText) < -5 || parseFloat(xText) > 3) {
        showNotification("Y value must be between -5 and 3.");
        isValid = false;
    }

    const yText = document.getElementsByName("coo_y")[0].value;
    if (!/^[+-]?([0-9]*[.])?[0-9]+$/.test(yText) || !isFinite(parseFloat(yText)) || parseFloat(yText) < -5 || parseFloat(yText) > 3) {
        showNotification("R value must be between -5 and 3.");
        isValid = false;
    }

    return isValid;
}


function validateR() {
    const r = document.getElementsByName("radius")[0].value;
    return r !== "";
}