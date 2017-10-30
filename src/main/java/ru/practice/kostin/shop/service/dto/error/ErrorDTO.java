package ru.practice.kostin.shop.service.dto.error;

public class ErrorDTO {
    private String code;
    private String error;

    public ErrorDTO(String code, String error) {
        this.code = code;
        this.error = error;
    }

    public ErrorDTO() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ErrorDTO errorDTO = (ErrorDTO) o;

        if (code != null ? !code.equals(errorDTO.code) : errorDTO.code != null) return false;
        return error != null ? error.equals(errorDTO.error) : errorDTO.error == null;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (error != null ? error.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ErrorDTO{" +
                "code='" + code + '\'' +
                ", error='" + error + '\'' +
                '}';
    }
}
