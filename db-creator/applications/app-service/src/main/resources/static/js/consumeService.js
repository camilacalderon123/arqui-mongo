function submitForm() {
    var form = document.getElementById("createDocument");
    var formData = new FormData(form);
    var dbName = document.getElementById("dbNameInput").value;
    var xhr = new XMLHttpRequest();
    formData.append("dbName", dbName);
    xhr.open("POST", "api/createDocument", true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                // Se ha enviado exitosamente
                showSuccessMessage();
            } else {
                // Ocurrió un error
                alert("Error al crear el documento");
            }
        }
    };
    xhr.send(formData);
}

function showSuccessMessage() {
    var successMessage = document.getElementById("successMessage");
    successMessage.style.display = "block";
}

function sendToExecute() {
    var form = document.getElementById("executeComand");
    var formData = new FormData(form);
    var dbName = document.getElementById("comands").value;
    var xhr = new XMLHttpRequest();
    formData.append("command", dbName);
    xhr.open("POST", "api/executeCommand", true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                // Se ha enviado exitosamente
                showSuccessMessageExecuted();
                alert(xhr.responseText);
            } else {
                // Ocurrió un error
                alert("Error al ejecutar el comando");
            }
        }
    };
    xhr.send(formData);
}

function showSuccessMessageExecuted() {
    var successMessage = document.getElementById("successMessageCommand");
    successMessage.style.display = "block";
}