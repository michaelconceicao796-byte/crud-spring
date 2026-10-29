package crud.angular.demo.excepition;

public class RecordNotFoundExcepition extends RuntimeException{
    
    private static final long serialVersionUID = 1L;

    public RecordNotFoundExcepition(Long id) {
        super("Registro não encontrado com o id: " + id);
    }
}
