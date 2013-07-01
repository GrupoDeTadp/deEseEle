package tadp.injector.dsl

import tadp.dependency.injector.InjectionEngine
import tadp.dependency.injector.fixture.velador.Alumbrable
import tadp.dependency.injector.fixture.velador.Enchufable
import tadp.dependency.injector.fixture.velador.EnchufeTresPatas
import tadp.dependency.injector.fixture.velador.LamparitasOsram
import tadp.dependency.injector.fixture.velador.Velador
import tadp.dependency.injector.structure.InjectionStructure


class DSLDefinition {
		
	static public def un = "nada"
	static public def con = "nada"
	
	static{
		InjectionEngine.metaClass {
		}
	}
	
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
		def relation = new RelationBuilder(esQue: quienClass)
		relations.add(relation)
		return relation
	}
	
	def dame(claseDelObjeto) {
		aConstruir = claseDelObjeto
	}
	
	def build(){
		
		InjectionStructure structure = new InjectionStructure(){
			void defineParts() {
				relations.each {	
					define(it.quien, it.esQue);
				}
			}
		}
		
		def motor = new InjectionEngine(structure)
		motor.sparkOfLife(aConstruir)
	}
	
}

class RelationBuilder{
	def quien
	def esQue
	
	def como(DefClass) {
			esQue = DefClass
	}
}