package dh.backend.maxisoriano.ClinicaMVC.Dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OdontologoResponseDto {
    private Integer id;
    private Integer nroMatricula;
    private String nombre;
    private String apellido;
}