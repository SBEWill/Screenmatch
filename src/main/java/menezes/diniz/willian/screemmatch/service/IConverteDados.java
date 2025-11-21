package menezes.diniz.willian.screemmatch.service;

public interface IConverteDados {
 <T> T  obterDados (String json, Class<T> classe);// O T indica que é uma classe generica, tornando o metodo reaproveitado
}
