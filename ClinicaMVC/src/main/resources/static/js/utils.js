const setErrors = (mensaje, campo, isError) =>{
    if (isError){
        campo.classList.remove("is-valid");
        campo.classList.add("is-invalid");
        campo.nextElementSibling.classList.remove("valid-feedback");
        campo.nextElementSibling.classList.add("invalid-feedback");
        campo.nextElementSibling.textContent = mensaje;
    }else{
        campo.classList.remove("is-invalid");
        campo.classList.add("is-valid");
        campo.nextElementSibling.classList.remove("invalid-feedback");
        campo.nextElementSibling.classList.add("valid-feedback");
        campo.nextElementSibling.textContent = mensaje;
    }
}


const isEmpty = (message, e) => {
    const campo = e.target
    const campoValue = campo.value

    if (campoValue.length == 0) {
        setErrors(message, campo, true);
    }
}

const validarMail = (e) => {
    /*Input camputado desde el html*/
    const campo = e.target;
    /*El valor del input capturado*/
    const campoValue = campo.value.toLowerCase().trim();

    const regex = new RegExp(/^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/);
    if(campoValue.length < 4 || !regex.test(campoValue)){
        setErrors(`${campo.name} invalido.`, campo, true);
    }
    else{
        setErrors(`${campo.name} valido.`, campo, false);
    }
};

const validarContrasenia = (e) => {
    /*Input camputado desde el html*/
    const campo = e.target;
    /*El valor del input capturado*/
    const campoValue = campo.value;
    const regex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[^\W_]{6,16}$/;
    if(campoValue.length < 5 || !regex.test(campoValue)){
        setErrors(`La ${campo.name} debe tener al entre 6 y 16 caracteres, al menos un dígito, al menos una minúscula y al menos una mayúscula.`, campo, true);
    }
    else{
        setErrors(`${campo.name} valida.`, campo, false);
    }
};

const capitalizarTexto = (nombre) =>{
    return nombre.replace(/\b\w/g, function(char) {
        return char.toUpperCase();
    });
}

const validarTexto = (e) =>{
    const campo = e.target;
    const regex = /^[a-zA-Z]{3,}(?:\s[a-zA-Z]{3,}){0,1}$/;
    let textoNormalizado = capitalizarTexto(campo.value.trim().toLowerCase());
    if (!regex.test(textoNormalizado)) {
        setErrors(`${campo.name} invalido.`, campo, true);
    } else {
        setErrors(`${campo.name} valido.`, campo, false);
    }
}

const validarMatricula = (e) => {
    const campo = e.target;
    const regex = /^\d+$/;
    const campoValue = campo.value;

    if (!regex.test(campoValue) || campoValue<100) {
        setErrors(`${campo.name} inválida. Debe contener al menos 3 caracteres numéricos.`, campo, true);
    } else {
        setErrors(`${campo.name} válida.`, campo, false);
    }
}

const validarTelefono = (e) => {
    const campo = e.target;
    const regex = /^\d{8,}$/;
    const campoValue = campo.value;

    if (!regex.test(campoValue)) {
        setErrors(`${campo.name} inválido. Debe contener al menos 8 caracteres numéricos.`, campo, true);
    } else {
        setErrors(`${campo.name} válido.`, campo, false);
    }
};

const validarFechaDeNacimiento = (e) => {
    const campo = e.target;
    const campoValue = campo.value;
    const fechaMin = new Date("1900-01-01");
    const fechaMax = new Date();
    const fechaIngresada = new Date(campoValue);

    if (fechaIngresada < fechaMin || fechaIngresada > fechaMax) {
        setErrors(`${campo.name} inválida. La fecha debe estar entre 01/01/1900 y la fecha actual.`, campo, true);
        return false;
    } else {
        setErrors(`${campo.name} válida.`, campo, false);
        return true;
    }
};

const validarContraseniasIguales = (e, password) =>{
    let campo = e.target;
    let contrasenia_2 = campo.value;
    let contrasenia_1 = password.value;

    if (contrasenia_1 != contrasenia_2) {
        setErrors(`${campo.name} inválida. Debe ser igual al campo ${password.name}.`, campo, true);
        return false;
    } else {
        setErrors(`${campo.name} válida.`, campo, false);
        return true;
    }

}
