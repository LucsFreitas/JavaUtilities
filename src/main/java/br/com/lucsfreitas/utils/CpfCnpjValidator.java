package br.com.lucsfreitas.utils;

public class CpfCnpjValidator {
    private static final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
    private static final int[] pesoCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

    private static int calcularDigito(String str, int[] peso) {
        int soma = 0;
        for (int indice=str.length()-1; indice >= 0; indice-- ) {
            int digito = Integer.parseInt(str.substring(indice,indice+1));
            soma += digito*peso[peso.length-str.length()+indice];
        }
        soma = 11 - soma % 11;
        return soma > 9 ? 0 : soma;
    }

    private static String padLeftCpf(String text, char character) {
        return String.format("%11s", text).replace(' ', character);
    }

    private static String padLeftCnpj(String text, char character) {
        return String.format("%14s", text).replace(' ', character);
    }

    public static boolean isValidCPF(String cpf) {
        if (cpf == null || cpf.length() != 11){
            return false;
        }
        for (int j = 0; j < 10; j++){
            if (padLeftCpf(Integer.toString(j), Character.forDigit(j, 10)).equals(cpf)){
                return false;
            }
        }
        int digito1 = calcularDigito(cpf.substring(0,9), pesoCPF);
        int digito2 = calcularDigito(cpf.substring(0,9) + digito1, pesoCPF);
        return cpf.equals(cpf.substring(0,9) + digito1 + digito2);
    }

    public static boolean isValidCNPJ(String cnpj) {
        if (cnpj == null || cnpj.length() != 14) {
            return false;
        }
        for (int j = 0; j < 14; j++){
            if (padLeftCnpj(Integer.toString(j), Character.forDigit(j, 14)).equals(cnpj)){
                return false;
            }
        }
        int digito1 = calcularDigito(cnpj.substring(0,12), pesoCNPJ);
        int digito2 = calcularDigito(cnpj.substring(0,12) + digito1, pesoCNPJ);
        return cnpj.equals(cnpj.substring(0,12) + digito1 + digito2);
    }

    private CpfCnpjValidator(){
    }
}
