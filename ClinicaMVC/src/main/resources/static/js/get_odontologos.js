window.addEventListener('load', function(){

    const tablaOdontologos = document.querySelector("#cuerpo-tabla-odontologos");

    const get_odontologos = () =>{
        fetch("/odontologo")
        .then((response) => response.json())
        .then((data) => {
            console.log(data);
            tablaOdontologos.innerHTML = "";
            data.forEach((odontologo, index) => {
                const row = document.createElement("tr");

                row.innerHTML = `
                <td>${odontologo.id}</td>
                <td>${odontologo.matricula
                }</td>
                <td>${odontologo.nombre}</td>
                <td>${odontologo.apellido}</td>
                <td>
                    <div class="d-flex align-items-center justify-content-center">
                        <button type="button" class="btn btn-primary mx-1 fw-bold" data-bs-toggle="modal" data-bs-target="#editarOdontologoModal${odontologo.id}" onclick="capturarDatosDelOdontologoAUpdate(${odontologo.id})"><i class="fa-solid fa-pen-to-square"></i> Editar
                        </button>
                        <!-- Editar Odontologo-->
                        <section class="modal fade" id="editarOdontologoModal${odontologo.id}" tabindex="-1" aria-labelledby="editarOdontologoModal${odontologo.id}" aria-hidden="true">
                            <div class="modal-dialog modal-dialog-centered">
                                <div class="modal-content">
                                    <div class="modal-header bg-color-principal">
                                        <h5 class="modal-title text-white" id="editarOdontologoModal${odontologo.id}">Editar Odontologo</h5>
                                        <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body bg-color-fondo">
                                        <form id="form-editar-odontologo-${odontologo.id}">
                                            <div class="mb-3">
                                                <label for="matriculaOdontologoEditar${odontologo.id}" class="form-label fw-bolder">Número de Matrícula</label>
                                                <input type="number" class="form-control bg-input" id="matriculaOdontologoEditar${odontologo.id}" placeholder="Ingrese su número de matrícula"min="100" value="${odontologo.matricula}" required>
                                                <div id="matriculaOdontologoEditarError${odontologo.id}">
                                                </div>
                                            </div>
                                            <div class="mb-3">
                                                <label for="nombreOdontologoEditar${odontologo.id}" class="form-label fw-bolder">Nombre</label>
                                                <input type="text" class="form-control bg-input" id="nombreOdontologoEditar${odontologo.id}" placeholder="Ingrese su nombre"
                                                minlength="3" maxlength="25" value="${odontologo.nombre}" required>
                                                <div id="nombreOdontologoEditarError${odontologo.id}">
                                                </div>
                                            </div>
                                            <div class="mb-3">
                                                <label for="apellidoOdontologoEditar${odontologo.id}" class="form-label fw-bolder">Apellido</label>
                                                <input type="text" class="form-control bg-input" id="apellidoOdontologoEditar${odontologo.id}" placeholder="Ingrese su apellido"
                                                minlength="3" maxlength="25" value="${odontologo.apellido}" required>
                                                <div id="apellidoOdontologoEditarError${odontologo.id}">
                                                </div>
                                            </div>
                                            <div class="d-flex align-items-center justify-content-center">
                                                <button type="submit" id="botonGuardarCambiosOdontologo${odontologo.id}" class="btn btn-personalized-2 mx-1 fw-bold" aria-label="Guardar cambios">Guardar cambios</button>
                                                <button type="button" id="botonGuardarEliminarOdontologo${odontologo.id}" class="btn btn-personalized-1 mx-1 fw-bold" data-bs-dismiss="modal" aria-label="Close">Cancelar</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </section>
                        <button type="button" class="btn btn-danger fw-bold mx-1" onclick="eliminarOdontologo(${odontologo.id})"><i class="fa-solid fa-trash"></i> Eliminar
                        </button>
                    </div>
                </td>
                `;
            tablaOdontologos.appendChild(row);
            });
        })
        .catch((error) => {
            console.error("Error fetching data: ",error)
        });
    }

    get_odontologos();

});