package com.flavio.minhasfinancas.api.dto;

import lombok.Data;

@Data
public class AtualizaStatusDTO {
  private String status;

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public AtualizaStatusDTO(String status) {
	super();
	this.status = status;
}

public AtualizaStatusDTO() {
	super();
}

  
}


