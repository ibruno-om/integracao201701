package br.com.integracaobsus.impl;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import br.com.integracaobsus.factory.Barramento;
import br.com.integracaosigtap.connect.Connection;
import br.com.integracaosigtap.connect.ConnectionSUS;
import br.com.integracaosigtap.model.BaseProcedimento;
import br.com.integracaosigtap.model.Compatibilidade;
import br.com.integracaosigtap.model.CompatibilidadePossivel;
import br.com.integracaosigtap.model.FormaOrganizacao;
import br.com.integracaosigtap.model.Grupo;
import br.com.integracaosigtap.model.InstrumentoRegistro;
import br.com.integracaosigtap.model.Procedimento;

public class BSus implements Barramento {

	private String urlProcedimento = "https://servicoshm.saude.gov.br/sigtap/ProcedimentoService/v1";
	private String urlCompatibilidade = "https://servicoshm.saude.gov.br/sigtap/CompatibilidadeService/v1";
	private String urlCompatibilidadePossivel = "https://servicoshm.saude.gov.br/sigtap/CompatibilidadePossivelService/v1";
	private String urlGrupo = "https://servicoshm.saude.gov.br/sigtap/NivelAgregacaoService/v1";

	private Connection connection;

	public void setProperties(Properties properties) {
		try {
			if (properties != null) {
				urlProcedimento = (String) properties.get("URL_PROCEDIMENTO");
				urlCompatibilidade = (String) properties.get("URL_COMPATIBILIDADE");
				urlGrupo = (String) properties.get("URL_GRUPO");
				connection = new ConnectionSUS(properties);
			} else {
				connection = new ConnectionSUS();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<BaseProcedimento> pesquisarProcedimentos() {
		try {
			XMLInputFactory fabrica = XMLInputFactory.newFactory();

			StringReader rs = new StringReader(connection.getPesquisarProcedimentos(urlProcedimento));
			System.out.println(connection.getPesquisarProcedimentos(urlProcedimento));

			XMLEventReader reader = fabrica.createXMLEventReader(rs);

			BaseProcedimento procedimento = null;
			Set<BaseProcedimento> procedimentos = new HashSet<BaseProcedimento>();

			while (reader.hasNext()) {
				XMLEvent event = reader.nextEvent();

				if (event.isStartElement()) {
					StartElement startElement = event.asStartElement();

					if (startElement.getName().getLocalPart().equals("BaseProcedimento")) {
						procedimento = new BaseProcedimento();
					} else if (startElement.getName().getLocalPart().equals("codigo")) {
						event = reader.nextEvent();
						procedimento.setCodigo(event.asCharacters().getData());
					} else if (startElement.getName().getLocalPart().equals("nome")) {
						event = reader.nextEvent();
						procedimento.setNome(event.asCharacters().getData());
						procedimentos.add(procedimento);
					}
				}

			}

			return new ArrayList<BaseProcedimento>(procedimentos);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<Compatibilidade> pesquisarCompatibilidades() {

		try {
			XMLInputFactory factory = XMLInputFactory.newFactory();

			System.out.println(connection.getPesquisarCompatibilidades(urlCompatibilidade));
			StringReader rs = new StringReader(connection.getPesquisarCompatibilidades(urlCompatibilidade));

			XMLEventReader reader = factory.createXMLEventReader(rs);

			Compatibilidade compatibilidade = null;
			
			CompatibilidadePossivel compatibilidadePossivel = null;
			InstrumentoRegistro instrumentoPrimario = null;
			InstrumentoRegistro instrumentoSecundario = null;
			
			Procedimento procedimentoPrincipal = null;
			
			Set<Compatibilidade> compatibilidades = new HashSet<Compatibilidade>();

			while (reader.hasNext()) {
				XMLEvent event = reader.nextEvent();

				if (event.isStartElement()) {
					StartElement startElement = event.asStartElement();

					if (startElement.getName().getLocalPart().equals("Compatibilidade")) {
						compatibilidade = new Compatibilidade();
					} else if (startElement.getName().getLocalPart().equals("codigo")) {
						event = reader.nextEvent();
						compatibilidade.setCodigo(event.asCharacters().getData());
					} else if (startElement.getName().getLocalPart().equals("CompatibilidadePossivel")) {
						compatibilidadePossivel = new CompatibilidadePossivel();
						
						while (reader.hasNext()) {
							event = reader.nextEvent();
							if (event.isStartElement()) {
								startElement = event.asStartElement();
								if (startElement.getName().getLocalPart().equals("codigo")) {
									event = reader.nextEvent();
									compatibilidadePossivel.setCodigo(event.asCharacters().getData());
								} else if (startElement.getName().getLocalPart().equals("InstrumentoRegistroPrincipal")) {
									instrumentoPrimario = new InstrumentoRegistro();
									
									while (reader.hasNext()) {
										event = reader.nextEvent();
										if (event.isStartElement()) {
											startElement = event.asStartElement();
											if (startElement.getName().getLocalPart().equals("codigo")) {
												event = reader.nextEvent();
												instrumentoPrimario.setCodigo(event.asCharacters().getData());
											} else if (startElement.getName().getLocalPart().equals("nome")) {
												event = reader.nextEvent();
												instrumentoPrimario.setNome(event.asCharacters().getData());
												break;
											}
										}
									}
									
									compatibilidadePossivel.setInstrumentoRegistroPrimario(instrumentoPrimario);
									
								} else if (startElement.getName().getLocalPart().equals("InstrumentoRegistroSecundario")) {
									instrumentoSecundario = new InstrumentoRegistro();
									
									while (reader.hasNext()) {
										event = reader.nextEvent();
										if (event.isStartElement()) {
											startElement = event.asStartElement();
											if (startElement.getName().getLocalPart().equals("codigo")) {
												event = reader.nextEvent();
												instrumentoSecundario.setCodigo(event.asCharacters().getData());
											} else if (startElement.getName().getLocalPart().equals("nome")) {
												event = reader.nextEvent();
												instrumentoSecundario.setNome(event.asCharacters().getData());
												break;
											}
										}
									}
									
									compatibilidadePossivel.setInstrumentoRegistroSecundario(instrumentoSecundario);
									
								} else if (startElement.getName().getLocalPart().equals("tipoCompatibilidade")) {
									event = reader.nextEvent();
									compatibilidadePossivel.setTipoCompatibilidade(event.asCharacters().getData());
									break;
								}
							}
						}
						
						compatibilidade.setCompatibilidadePossivel(compatibilidadePossivel);
						
					} else if (startElement.getName().getLocalPart().equals("ProcedimentoPrincipal")){
						
						procedimentoPrincipal = new Procedimento();
						
						while (reader.hasNext()) {
							event = reader.nextEvent();
							if (event.isStartElement()) {
								startElement = event.asStartElement();
								
								if (startElement.getName().getLocalPart().equals("codigo")) {
									event = reader.nextEvent();
									procedimentoPrincipal.setCodigo(event.asCharacters().getData());
								} else if (startElement.getName().getLocalPart().equals("nome")) {
									event = reader.nextEvent();
									procedimentoPrincipal.setNome(event.asCharacters().getData());
								} 
							}
						}
						
						compatibilidade.setProcedimentoPrimario(procedimentoPrincipal);
						compatibilidades.add(compatibilidade);
					}
				}

			}

			return new ArrayList<Compatibilidade>(compatibilidades);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	public List<Grupo> listarGrupos() {
		try {
			XMLInputFactory factory = XMLInputFactory.newFactory();

			StringReader rs = new StringReader(connection.getListarGrupos(urlGrupo));

			XMLEventReader reader = factory.createXMLEventReader(rs);

			Grupo grupo = null;
			Set<Grupo> grupos = new HashSet<Grupo>();

			while (reader.hasNext()) {
				XMLEvent event = reader.nextEvent();

				if (event.isStartElement()) {
					StartElement startElement = event.asStartElement();

					if (startElement.getName().getLocalPart().equals("Grupo")) {
						grupo = new Grupo();
					} else if (startElement.getName().getLocalPart().equals("codigo")) {
						event = reader.nextEvent();
						grupo.setCodigo(event.asCharacters().getData());
					} else if (startElement.getName().getLocalPart().equals("nome")) {
						event = reader.nextEvent();
						grupo.setNome(event.asCharacters().getData());
						grupos.add(grupo);
					}
				}

			}

			return new ArrayList<Grupo>(grupos);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<Grupo> listarSubGrupos() {
		try {
			XMLInputFactory factory = XMLInputFactory.newFactory();

			StringReader rs = new StringReader(connection.getListarSubGrupos(urlGrupo));

			XMLEventReader reader = factory.createXMLEventReader(rs);

			Grupo subGrupo = null;
			Grupo grupo = null;
			Set<Grupo> grupos = new HashSet<Grupo>();

			while (reader.hasNext()) {
				XMLEvent event = reader.nextEvent();

				if (event.isStartElement()) {
					StartElement startElement = event.asStartElement();

					if (startElement.getName().getLocalPart().equals("Subgrupo")) {
						subGrupo = new Grupo();
					} else if (startElement.getName().getLocalPart().equals("codigo")) {
						event = reader.nextEvent();
						subGrupo.setCodigo(event.asCharacters().getData());
					} else if (startElement.getName().getLocalPart().equals("nome")) {
						event = reader.nextEvent();
						subGrupo.setNome(event.asCharacters().getData());
						grupos.add(subGrupo);
					} else if (startElement.getName().getLocalPart().equals("Grupo")) {
						grupo = new Grupo();

						while (reader.hasNext()) {
							event = reader.nextEvent();
							if (event.isStartElement()) {
								startElement = event.asStartElement();
								if (startElement.getName().getLocalPart().equals("codigo")) {
									event = reader.nextEvent();
									grupo.setCodigo(event.asCharacters().getData());
								} else if (startElement.getName().getLocalPart().equals("nome")) {
									// último elemento desta estrutura
									event = reader.nextEvent();
									grupo.setNome(event.asCharacters().getData());
									break;
								}
							}
						}

						subGrupo.setGrupo(grupo);
					}
				}

			}

			return new ArrayList<Grupo>(grupos);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<Grupo> pesquisarGrupos() {
		return null;
	}
	
	
	public List<Procedimento> getDetalharProcedimentos(){
		try {
			XMLInputFactory fabrica = XMLInputFactory.newFactory();

			StringReader rs = new StringReader(connection.getDetalharProcedimentos(urlProcedimento));
			System.out.println(connection.getDetalharProcedimentos(urlProcedimento));

			XMLEventReader reader = fabrica.createXMLEventReader(rs);

			Procedimento procedimento = null;
			FormaOrganizacao formaOrganizacao = null;
			Grupo subGrupo = null;
			Grupo grupo = null;
			
			Set<Procedimento> procedimentos = new HashSet<Procedimento>();

			while (reader.hasNext()) {
				XMLEvent event = reader.nextEvent();

				if (event.isStartElement()) {
					StartElement startElement = event.asStartElement();

					if (startElement.getName().getLocalPart().equals("Procedimento")) {
						procedimento = new Procedimento();
					} else if (startElement.getName().getLocalPart().equals("codigo")) {
						event = reader.nextEvent();
						procedimento.setCodigo(event.asCharacters().getData());
					} else if (startElement.getName().getLocalPart().equals("nome")) {
						event = reader.nextEvent();
						procedimento.setNome(event.asCharacters().getData());
					} else if (startElement.getName().getLocalPart().equals("FormaOrganizacao")) {
						
						formaOrganizacao = new FormaOrganizacao();
						while (reader.hasNext()) {
							event = reader.nextEvent();
							if (event.isStartElement()) {
								startElement = event.asStartElement();
								if (startElement.getName().getLocalPart().equals("codigo")) {
									event = reader.nextEvent();
									formaOrganizacao.setCodigo(event.asCharacters().getData());
								} else if (startElement.getName().getLocalPart().equals("nome")) {
									event = reader.nextEvent();
									formaOrganizacao.setNome(event.asCharacters().getData());
								} else if (startElement.getName().getLocalPart().equals("Subgrupo")){
									
									subGrupo = new Grupo();
									while (reader.hasNext()) {
										event = reader.nextEvent();
										if (event.isStartElement()) {
											startElement = event.asStartElement();
											if (startElement.getName().getLocalPart().equals("codigo")) {
												event = reader.nextEvent();
												subGrupo.setCodigo(event.asCharacters().getData());
											} else if (startElement.getName().getLocalPart().equals("nome")) {
												event = reader.nextEvent();
												subGrupo.setNome(event.asCharacters().getData());
											} else if (startElement.getName().getLocalPart().equals("Subgrupo")){
												
												grupo = new Grupo();
												while (reader.hasNext()) {
													event = reader.nextEvent();
													if (event.isStartElement()) {
														startElement = event.asStartElement();
														if (startElement.getName().getLocalPart().equals("codigo")) {
															event = reader.nextEvent();
															subGrupo.setCodigo(event.asCharacters().getData());
														} else if (startElement.getName().getLocalPart().equals("nome")) {
															event = reader.nextEvent();
															subGrupo.setNome(event.asCharacters().getData());
															break;
														}
													}
												}
												break;
											}
										}
									}
									break;
								}
							}
						}
						procedimentos.add(procedimento);
					}
				}

			}

			return new ArrayList<Procedimento>(procedimentos);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<CompatibilidadePossivel> listarCompatibilidadesPossiveis() {

		try {
			XMLInputFactory factory = XMLInputFactory.newFactory();

			StringReader rs = new StringReader(
					connection.getListarCompatibilidadesPossiveis(urlCompatibilidadePossivel));

			XMLEventReader reader = factory.createXMLEventReader(rs);

			CompatibilidadePossivel compatibilidadePossivel = null;

			InstrumentoRegistro instrumentoPrimario = null;
			InstrumentoRegistro instrumentoSecundario = null;

			Set<CompatibilidadePossivel> compatibilidadesPossiveis = new HashSet<CompatibilidadePossivel>();

			while (reader.hasNext()) {
				XMLEvent event = reader.nextEvent();

				if (event.isStartElement()) {
					StartElement startElement = event.asStartElement();

					if (startElement.getName().getLocalPart().equals("CompatibilidadePossivel")) {
						compatibilidadePossivel = new CompatibilidadePossivel();
					} else if (startElement.getName().getLocalPart().equals("codigo")) {
						event = reader.nextEvent();
						compatibilidadePossivel.setCodigo(event.asCharacters().getData());

					} else if (startElement.getName().getLocalPart().equals("InstrumentoRegistroPrincipal")) {
						instrumentoPrimario = new InstrumentoRegistro();

						while (reader.hasNext()) {
							event = reader.nextEvent();
							if (event.isStartElement()) {
								startElement = event.asStartElement();
								if (startElement.getName().getLocalPart().equals("codigo")) {
									event = reader.nextEvent();
									instrumentoPrimario.setCodigo(event.asCharacters().getData());
								} else if (startElement.getName().getLocalPart().equals("nome")) {
									// último elemento desta estrutura
									event = reader.nextEvent();
									instrumentoPrimario.setNome(event.asCharacters().getData());
									break;
								}
							}
						}

						compatibilidadePossivel.setInstrumentoRegistroPrimario(instrumentoPrimario);

					} else if (startElement.getName().getLocalPart().equals("InstrumentoRegistroSecundario")) {
						instrumentoSecundario = new InstrumentoRegistro();

						while (reader.hasNext()) {
							event = reader.nextEvent();
							if (event.isStartElement()) {
								startElement = event.asStartElement();
								if (startElement.getName().getLocalPart().equals("codigo")) {
									event = reader.nextEvent();
									instrumentoSecundario.setCodigo(event.asCharacters().getData());
								} else if (startElement.getName().getLocalPart().equals("nome")) {
									// último elemento desta estrutura
									event = reader.nextEvent();
									instrumentoSecundario.setNome(event.asCharacters().getData());
									break;
								}
							}
						}

						compatibilidadePossivel.setInstrumentoRegistroSecundario(instrumentoSecundario);

					} else if (startElement.getName().getLocalPart().equals("tipoCompatibilidade")) {
						event = reader.nextEvent();
						compatibilidadePossivel.setTipoCompatibilidade(event.asCharacters().getData());
						compatibilidadesPossiveis.add(compatibilidadePossivel);
						continue;
					}

				}

			}

			return new ArrayList<CompatibilidadePossivel>(compatibilidadesPossiveis);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

}
