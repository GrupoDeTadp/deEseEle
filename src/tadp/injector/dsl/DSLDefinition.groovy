package tadp.injector.dsl

import tadp.dependency.injector.InjectionEngine
import tadp.dependency.injector.fixture.velador.Alumbrable
import tadp.dependency.injector.fixture.velador.Enchufable
import tadp.dependency.injector.fixture.velador.EnchufeTresPatas
import tadp.dependency.injector.fixture.velador.LamparitasOsram
import tadp.dependency.injector.fixture.velador.Mampara;
import tadp.dependency.injector.fixture.velador.MamparaConDibujitos;
import tadp.dependency.injector.fixture.velador.MamparaSobria;
import tadp.dependency.injector.fixture.velador.Velador
import tadp.dependency.injector.structure.InjectionStructure
import tadp.dependency.injector.structure.SetterInjectionStructure
import tadp.dependency.injector.fixture.velador.SetterVeladorConOsramStructure

class DSLDefinition {
		
	
	static def motorDeInyeccion(bloque) { 
		def ib = new InjectionBuilder()
		ib.with bloque
		ib.build()
	}
	
	
}

class InjectionBuilder{
	def relations = []
	def aConstruir
			
	def usando( quienClass ) {
		def relation = new RelationBuilder(quien: quienClass)
		relations.push(relation)
		return relation
	}

	
	def dame(claseDelObjeto) {
		aConstruir = claseDelObjeto
	}
	
	
	
	def build(){
		/*InjectionStructure structure = new InjectionStructure(){
			void defineParts() {
				relations.each {	
					define(it.quien, it.esQue);
				}
			}
		}*/
		//def InjectionStructure structure = new SetterVeladorConOsramStructure()
		def motor = InjectionEngine.createEngine(buildStructureFrom(relations));
		motor.sparkOfLife(aConstruir)
	}
	
	def buildStructureFrom(relations){
		def InjectionStructure structure = new GenericStructure(relations)
		structure.imprimiteAlgo()
		structure
	}
	
}

class GenericStructure extends SetterInjectionStructure
{
	def relations
	GenericStructure(relaciones){
		relations = relaciones
	}
	
	def imprimiteAlgo(){println relations.size()}
	
	@Override
	void defineParts() {
		relations.each {
					define(it.quien, it.esQue);
				}
	}
	/*@Override
	public void defineParts() {
		this.define(Velador.class, Velador.class);
		this.define(Alumbrable.class, LamparitasOsram.class);
		this.define(Mampara.class, MamparaSobria.class);
		this.define(MamparaConDibujitos.class, MamparaConDibujitos.class);
		
		
		this.injectProperty(Velador.class, "luz");
		this.injectProperty(Velador.class, "marca", "Cuchuflo");
		
		this.injectPropertyList(Velador.class, "mamparas", MamparaConDibujitos.class, Mampara.class);		
	}*/
	
	
}

class RelationBuilder{
	def quien
	def esQue
	
	def como(DefClass) {
			esQue = DefClass
	}
}