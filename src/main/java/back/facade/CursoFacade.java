package back.facade;

import back.business.CursoBusiness;
import model.Curso;

import java.util.Map;
import java.util.UUID;

public class CursoFacade {

    private CursoBusiness cursoBusiness;

    public CursoFacade(CursoBusiness cursoBusiness) {
        this.cursoBusiness = cursoBusiness;
    }

    public CursoFacade() {
        this.cursoBusiness = new CursoBusiness();
    }

    public Map<UUID, Curso> listarCursos(){
        return cursoBusiness.listarCursos();
    }

    public void guardarCursos(){
        cursoBusiness.guardarCursos();
    }

    public CursoBusiness getCursoBusiness() {
        return cursoBusiness;
    }

    public void setCursoBusiness(CursoBusiness cursoBusiness) {
        this.cursoBusiness = cursoBusiness;
    }
}
