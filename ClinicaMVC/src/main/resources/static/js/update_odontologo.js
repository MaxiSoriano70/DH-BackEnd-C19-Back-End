const capturarDatosDelOdontologoAUpdate = (id) => {
    console.log("Recibimos el ID del odontologo:", id);
    const formulario = document.querySelector(`#form-editar-odontologo-${id}`);

    const matricula = document.querySelector(`#matriculaOdontologoEditar${id}`);
    const nombre = document.querySelector(`#nombreOdontologoEditar${id}`);
    const apellido = document.querySelector(`#apellidoOdontologoEditar${id}`);

    matricula.addEventListener("input", e => validarMatricula(e));
    nombre.addEventListener("input", e => validarTexto(e));
    apellido.addEventListener("input", e => validarTexto(e));

    matricula.addEventListener("blur", e => isEmpty(`Se requiere que ingrese su ${matricula.name}`, e));
    nombre.addEventListener("blur", e => isEmpty(`Se requiere que ingrese su ${nombre.name}`, e));
    apellido.addEventListener("blur", e => isEmpty(`Se requiere que ingrese su ${apellido.name}`, e));

    const urlApi = "http://localhost:8080";

    formulario.addEventListener('submit', function(event){
        event.preventDefault();
        const payload = {
            id: id,
            apellido: apellido.value,
            matricula: matricula.value,
            nombre: nombre.value
        }

        const settings = {
            method: "PUT",
            body: JSON.stringify(payload),
            headers: {
                "Content-Type": "application/json",
            },
        }

        realizarUpdateOdontologo(settings);
        formulario.reset();
    });

    const realizarUpdateOdontologo = (settings) => {
        fetch(`/odontologo`, settings)
        .then((response) => {
            if (!response.ok) {
                throw new Error('No se pudo actualizar el odontólogo');
            }
            return response.json();
        })
        .then((data) => {
            console.log(data);
            Swal.fire({
                icon: "success",
                title: "¡Éxito!",
                text: "Cambios guardados",
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
                text: "No se pudo guardar los cambios",
                confirmButtonColor: "#456584",
                confirmButtonBorderColor: "#3e5975",
                textColor: "#000",
                background: "#eaeef4",
            });
        });
    };
}

const eliminarOdontologo = (id) => {
    const urlApi = "http://localhost:8080";

    Swal.fire({
        title: "Eliminar Odontólogo",
        text: "¿Estás seguro de que deseas eliminar este Odontólogo?",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#456584",
        confirmButtonBorderColor: "#3e5975",
        cancelButtonColor: "#dc3545",
        textColor: "#000",
        background: "#eaeef4",
        confirmButtonText: "Sí, eliminar",
        cancelButtonText: "Cancelar",
    }).then((result) => {
        if (result.isConfirmed) {
            const settings = {
                method: "DELETE",
                headers: {
                    "Content-Type": "application/json",
                }
            };

            fetch(`/odontologo/${id}`, settings)
            .then(response => {
                if (!response.ok) {
                    throw new Error('No se pudo eliminar el odontólogo');
                }
                return response.json();
            })
            .then(data => {
                console.log(data);
                Swal.fire({
                    title: "¡Eliminado con éxito!",
                    text: "El odontólogo ha sido eliminado.",
                    icon: "success",
                    confirmButtonColor: "#456584",
                    confirmButtonBorderColor: "#3e5975",
                    textColor: "#000",
                    background: "#eaeef4",
                }).then(() => {
                    location.reload();
                });
            })
            .catch(error => {
                console.error('Error al eliminar:', error);
                Swal.fire({
                    title: "Error",
                    text: "No se pudo eliminar el odontólogo.",
                    icon: "error",
                    confirmButtonColor: "#456584",
                    confirmButtonBorderColor: "#3e5975",
                    textColor: "#000",
                    background: "#eaeef4",
                });
            });
        }
    });
};