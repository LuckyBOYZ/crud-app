// dodawanie nowego pacjenta do bazy danych
$("#button-patient-submit").click(function () {
    const firstName = $("#firstName").val();
    const lastName = $("#lastName").val();
    const pesel = $("#pesel").val();
    const patient = {
        firstName: firstName,
        lastName: lastName,
        pesel: pesel
    };
    console.log($("#firstName"));
    $.ajax({
        url: "http://localhost:8080/patients",
        method: "post",
        data: JSON.stringify(patient),
        contentType: "application/json",
        success: function () {
            alert("Dodano nowego pacjenta");
            firstPage();
            reloadPatientsRows();
            $("#firstName").val('');
            $("#lastName").val('');
            $("#pesel").val('');
        },
        error: function (message) {
            console.log(message.responseText);
            alert(message.responseText);
        }
    })
});

// ładowane są dane pacjentów z bazy danych
function reloadPatientsRows() {
    $.ajax({
        url: "http://localhost:8080/patients",
        method: "get",
        success: function (patients) {
            $("#patient-rows > tr:not(#row-template)").remove();
            const $rowTemplate = $("#row-template");
            for (let i = 0; i < patients.length; i++) {
                const patient = patients[i];
                const $row = $rowTemplate.clone();
                $row.removeAttr("id");
                $row.css("display", "table-row");
                $row.find(".cell-firstName").text(patient.firstName);
                $row.find(".cell-lastName").text(patient.lastName);
                $row.find(".cell-pesel").text(patient.pesel);
                $row.find(".remove-patient");
                $row.find(".edit-patient");
                $("#patient-rows").append($row);
                $row.find(".remove-patient").click(function () {
                    $.ajax({
                        url: "http://localhost:8080/patients/" + patient.id,
                        method: "delete",
                        success: function () {
                            reloadPatientsRows();
                        },
                        error: function () {
                            alert("coś jest nie tak");
                        }
                    })
                });
                $row.find(".edit-patient").click(function () {
                    $("#div-edit").show();
                    $("#div-all-patients").hide();
                    $("#div-form-add-patient").hide();
                    $("#util").hide();
                    $(".first-name-edit").val(patient.firstName);
                    $(".last-name-edit").val(patient.lastName);
                    $(".pesel-edit").val(patient.pesel);
                    $("#div-edit").data("patient", patient);
                })
            }
        }
    });
}
//przycisk powrotu na główną stronę
$(".button-to-back-on-first-page").click(function () {
    firstPage();
    reloadPatientsRows();
});

//strona startowa
function firstPage() {
    $("#div-edit").hide();
    $("#div-form-add-patient").hide();
    $("#div-all-patients").show();
    $("#util").show();
}

//przycisk przejścia na stronę dodawania nowego pacjenta
$("#input-add-patient").click(function () {
    $("#div-edit").hide();
    $("#div-form-add-patient").show();
    $("#div-all-patients").hide();
    $("#util").hide();
});

//edycja danych pacjenta w bazie danych
$("#button-to-edit").click(function () {
    const editPatient = $("#div-edit").data("patient");
    editPatient.firstName = $(".first-name-edit").val();
    editPatient.lastName = $(".last-name-edit").val();
    editPatient.pesel = $(".pesel-edit").val();
    console.log(editPatient);
    $.ajax({
        url: "http://localhost:8080/patients/edit/" + editPatient.id,
        method: "put",
        data: JSON.stringify(editPatient),
        contentType: "application/json",
        success: function () {
            alert("Dane pacjenta zostały edytowane");
            firstPage();
            reloadPatientsRows();
        },
        error: function (message) {
            console.log(message.responseText);
            alert(message.responseText);
        }
    })
});

// wyszukiwanie pacjenta po imieniu, nazwisku, lub numerze pesel
$("#input-search").keyup(function () {
    let value = $(this).val().toLowerCase();
    $("#patient-rows tr").filter(function () {
        $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1);
    });
    $("#row-template").hide();
});

firstPage();
reloadPatientsRows();
