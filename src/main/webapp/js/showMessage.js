
function showNotification(message) {
    const notification = document.getElementById("error_div");
    notification.innerHTML = message;
    notification.style.display = "block";

    // Hide the notification after 3 seconds (3000 milliseconds)
    setTimeout(() => {
        notification.style.display = "none";
    }, 3000);
}