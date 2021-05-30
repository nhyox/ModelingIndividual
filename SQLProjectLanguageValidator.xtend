/*
 * generated by Xtext 2.24.0
 */
package org.xtext.example.mydsl.validation

import java.util.List
import org.eclipse.xtext.validation.Check
import org.xtext.example.mydsl.sQLProjectLanguage.Attribute
import org.xtext.example.mydsl.sQLProjectLanguage.SQLProjectLanguagePackage.Literals
import org.xtext.example.mydsl.sQLProjectLanguage.Add
import org.xtext.example.mydsl.sQLProjectLanguage.Remove
import org.xtext.example.mydsl.sQLProjectLanguage.Select
import org.xtext.example.mydsl.sQLProjectLanguage.PrimaryKey
import java.util.Map
import java.util.HashMap

/** 
 * This class contains custom validation rules. 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#validation
 */
class SQLProjectLanguageValidator extends AbstractSQLProjectLanguageValidator {

	@Check
	def checkAttributesAdd(Add add){
		for(Attribute att : add.attributes){
			if(att.type == 'String' && att.requirement1 !== null){
				error('One of the attributes is of type String, but has integer value or operator', Literals.ADD__ATTRIBUTES)
			}else if (att.type == 'int' && att.stringBute !== null){
				error('One of the attributes is of type int, but has String value', Literals.ADD__ATTRIBUTES)
			}else if (add.primaryKey !== null && add.primaryKey.type == 'String' && add.primaryKey.requirement1 !== null ){
				error('Primary key is of value String, but has integer value or operator', Literals.ADD__PRIMARY_KEY)
			}else if (add.primaryKey !== null && add.primaryKey.type == 'int' && add.primaryKey.keyStr !== null){
				error('Primary key is of type int, but has String value', Literals.ADD__PRIMARY_KEY)
				}
			}			
	}
	
	@Check
	def checkAttributesRemove(Remove remove){
		for(Attribute att : remove.attributes){
			if(att.type == 'String' && att.requirement1 !== null){
				error('One of the attributes is of type String, but has integer value or operator', Literals.REMOVE__ATTRIBUTES)
			}else if (att.type == 'int' && att.stringBute !== null){
				error('One of the attributes is of type int, but has String value', Literals.REMOVE__ATTRIBUTES)
			}
		}
	}
	
	@Check
	def checkAttributesSelect(Select select){
		for(Attribute att : select.attributes){
			if(att.type == 'String' && att.requirement1 !== null){
				error('One of the attributes is of type String, but has integer value or operator', Literals.SELECT__ATTRIBUTES)
			}else if (att.type == 'int' && att.stringBute !== null){
				error('One of the attributes is of type int, but has String value', Literals.SELECT__ATTRIBUTES)
			}
		}
	}
	
	@Check 
	def checkAttributeNamesAdd(Add add){
		val map = new HashMap<String, Integer>
		for(Attribute att : add.attributes){
			if(map.containsKey(att.name)){
				var attNameCounter = map.get(att.name)
				map.put(att.name, attNameCounter= attNameCounter+1)
			}else{
				map.put(att.name, 1)
			}	
		}		
		for(Integer i : map.values()){
				val value = 2
				if(i >= value){
					error('Multiple attribute names are the same', Literals.ADD__ATTRIBUTES)
				}
			}
	}
	
	
		@Check 
	def checkAttributeNamesRemove(Remove remove){
		val map = new HashMap<String, Integer>
		for(Attribute att : remove.attributes){
			if(map.containsKey(att.name)){
				var attNameCounter = map.get(att.name)
				map.put(att.name, attNameCounter= attNameCounter+1)
			}else{
				map.put(att.name, 1)
			}	
		}		
		for(Integer i : map.values()){
				val value = 2
				if(i >= value){
					error('Multiple attribute names are the same', Literals.REMOVE__ATTRIBUTES)
				}
			}
	}
	
			@Check 
	def checkAttributeNamesSelect(Select select){
		val map = new HashMap<String, Integer>
		for(Attribute att : select.attributes){
			if(map.containsKey(att.name)){
				var attNameCounter = map.get(att.name)
				map.put(att.name, attNameCounter= attNameCounter+1)
			}else{
				map.put(att.name, 1)
			}	
		}		
		for(Integer i : map.values()){
				val value = 2
				if(i >= value){
					error('Multiple attribute names are the same', Literals.SELECT__ATTRIBUTES)
				}
			}
	}
	
	
	
}