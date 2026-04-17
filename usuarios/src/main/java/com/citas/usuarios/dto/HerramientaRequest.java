package com.citas.usuarios.dto;

public class HerramientaRequest {
    private Long idtool;
    private String tituloTool;
    private String autorTool;
    private String toolEstado;
    private boolean dispoTool;
    private String userTool;

    public HerramientaRequest(Long idtool, String tituloTool, String autorTool, String toolEstado, boolean dispoTool,
            String userTool) {
        this.idtool = idtool;
        this.tituloTool = tituloTool;
        this.autorTool = autorTool;
        this.toolEstado = toolEstado;
        this.dispoTool = dispoTool;
        this.userTool = userTool;
    }

    public HerramientaRequest() {
    }

    public Long getIDTool() {
        return idtool;
    }

    public String getTituloAutor() {
        return tituloTool;
    }

    public String getAutorTool() {
        return autorTool;
    }

    public String getToolEstado() {
        return toolEstado;
    }

    public Boolean getDispoTool() {
        return dispoTool;
    }

    public String getToolUser() {
        return userTool;
    }

    public void setToolEstado(String toolEstado) {
        this.toolEstado = toolEstado;
    }

    public void setDispoTool(Boolean dispoTool) {
        this.dispoTool = dispoTool;
    }

    public void setIDTool(Long idtool) {
        this.idtool = idtool;
    }

    public void setTituloTool(String tituloTool) {
        this.tituloTool = tituloTool;
    }

    public void setAutorTool(String autorTool) {
        this.autorTool = autorTool;
    }

    public void setToolUser(String userTool) {
        this.userTool = userTool;
    }

}
