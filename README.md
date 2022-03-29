# jtg

Generic java template generator.

Generator is based on XML schema to define data model. XML schema isn't predefined. Each project can have its own data model. 
Data model is used to load and validate project file as a input for generation. JSP-like templates are used to generate different 
files based on project file data.

Generation starts with one start template which can run other templates and produce multiple different outputs.

Macro language for this generator is java. But template can generate any text file.

Using only two components jtg.basics and jtg.core it is possible to use generator in you project to generate for example e-mails etc.

# History

First idea to make own generic text generator I've got in 2005 as I worked for Deutsche Post AG and have made temporare WSDL compiler for
custom framework. 
I've used http://velocity.apache.org/. But in this engine you should control generation process from you program. It means you need some 
control code, which starts velocity engine for different output types, for multiple different objects. Futhermore you need to collect 
different java objects and put it into hashmap depends on what one or other template needs.

In 2006 we founded with 7 other developer small company. And at start, as we don't have customer projects for all employees, we've decided
to implement this generator using modern and fast growing .NET framework. This generator was developed between 2006 until now. You can ask
at [idesis GmbH](http://www.idesis.de/unsere-produkte/codesis/) for codesis (c)

Sinse 2011 I've developed from scratch new generator for java JVM as eclipse plugin. This generator is used in many project and accelerate 
development up to 40-70%.

# Copyright

I've used in with my generator [eclipse colorer plugin](http://colorer.sourceforge.net/), which was written by Igor Russkih.
I've only developed some coloring schema for jtg specifical file formats.

# Update 2019-11-21
The jtg is updated. It is now compatible with eclipse 2019-09 R and works with OpenJDK 9 - 12.

# Update 2021-07-13
The jtg is updated. Implemented cache to optimize compilation experience. Implemented leader -> multi follower project configuration in eclipse. This configuration makes possible to use one shema/template set for different following project and maintain shema/template set separately.
It is now compatible with eclipse 2021-06.

# Update site
Please use https://raw.githubusercontent.com/dgo42/jtg-update/master/site.xml in eclipse as an update site to install JTG

# TBD

One template generator is not really usable without template set for some usual software projects. 
Shortly I public also some demo project, based on spring boot framework, having set of templates to create:
  * database schema
  * stored procedures & views
  * java beans for DB
  * DAO classes
  * services for CRUD operation and interface for custom business functions
  * controllers for CRUD operations
  * [ExtJS (c) data models](http://docs.sencha.com/extjs/6.5.3/modern/Ext.data.Model.html)
  * [ExtJS (c) data store](http://docs.sencha.com/extjs/6.5.3/modern/Ext.data.Store.html)
  * [ExtJS (c) grid panel](http://docs.sencha.com/extjs/6.5.3/modern/Ext.grid.Grid.html)
