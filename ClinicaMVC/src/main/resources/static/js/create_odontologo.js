window.addEventListener('load', function () {
    const formularioAgregarOdontologo = document.querySelector("#form-agregar-odontologo");
    const matriculaAgregarOdontologo = document.querySelector("#matriculaOdontologoAgregar");
    const nombreAgregarOdontologo = document.querySelector("#nombreOdontologoAgregar");
    const apellidoAgregarOdontologo = document.querySelector("#apellidoOdontologoAgregar");

    matriculaAgregarOdontologo.addEventListener("input", e => validarMatricula(e));
    nombreAgregarOdontologo.addEventListener("input", e => validarTexto(e));
    apellidoAgregarOdontologo.addEventListener("input", e => validarTexto(e));

    matriculaAgregarOdontologo.addEventListener("blur", e => isEmpty(`Se requiere que ingrese su ${matriculaAgregarOdontologo.name}`, e));
    nombreAgregarOdontologo.addEventListener("blur", e => isEmpty(`Se requiere que ingrese su ${nombreAgregarOdontologo.name}`, e));
    apellidoAgregarOdontologo.addEventListener("blur", e => isEmpty(`Se requiere que ingrese su ${apellidoAgregarOdontologo.name}`, e));

    const urlApi = "http://localhost:8080";

    formularioAgregarOdontologo.addEventListener('submit', function(event){
        event.preventDefault();

        const payload = {
            apellido: apellidoAgregarOdontologo.value,
            matricula: matriculaAgregarOdontologo.value,
            nombre: nombreAgregarOdontologo.value
        }

        const settings = {
            method: "POST",
            body: JSON.stringify(payload),
            headers: {
                "Content-Type": "application/json",
            },
        }

        realizarAgregarOdontologo(settings);
        formularioAgregarOdontologo.reset();
    });

    const realizarAgregarOdontologo = (settings) => {
        fetch(`/odontologo`,settings)
        .then((response) => response.json())
        .then((data) => {
            console.log(data);
            Swal.fire({
                icon: "success",
                title: "¡Exito!",
                text: `Odontologo agregado`,
                showConfirmButton: false,
                textColor: "#000",
                background: "#eaeef4",
                timer: 1500
            });
            setTimeout(() => {
                location.replace("../pages/odontologos.html");
            }, 1500);
        })
        .catch((error) => {
            console.log(error);
            Swal.fire({
                icon: "error",
                title: "Oops...",
                text: "No se pudo agregar el odontologo",
                confirmButtonColor: "#456584",
                confirmButtonBorderColor: "#3e5975",
                textColor: "#000",
                background: "#eaeef4",
            });
        })
    }

});