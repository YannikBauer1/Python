<?xml version="1.0" encoding="UTF-8"?>

<!DOCTYPE rdf:RDF [
 <!ENTITY rdf  "http://www.w3.org/1999/02/22-rdf-syntax-ns#">
 <!ENTITY rdfs "http://www.w3.org/2000/01/rdf-schema#">
]>

<xsl:stylesheet version="1.0" 
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:dc="http://purl.org/dc/elements/1.1/"
	xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#" 
	xmlns:rdfs="http://www.w3.org/2000/01/rdf-schema#" 
	xmlns:foaf="http://xmlns.com/foaf/0.1/">
	
	<xsl:output method="xml" encoding="UTF-8" />

	<xsl:template match="document/minutes/meeting">
		<rdf:RDF>
			<rdf:Description rdf:about="{@ID}">
				<rdf:type rdf:resource="Meeting"/>

				<xsl:call-template name="information"/>

				<xsl:call-template name="persons"/>			

				<xsl:call-template name="topics"/>

			</rdf:Description>
		</rdf:RDF>
	</xsl:template>

	<xsl:template name="information">
		<rdf:OrganName>
			<xsl:value-of select="generalInformation/organName" />
		</rdf:OrganName>
		<rdf:Local>
			<xsl:value-of select="generalInformation/local" />
		</rdf:Local>
		<dc:date>
			<xsl:value-of select="generalInformation/date/day" 
			/>-<xsl:value-of select="generalInformation/date/month" 
			/>-<xsl:value-of select="generalInformation/date/year" 
			/>,<xsl:value-of select="generalInformation/time/startingTime/hour" 
			/>:<xsl:value-of select="generalInformation/time/startingTime/minute" />
		</dc:date>
	</xsl:template>

	<xsl:template name="persons">
		<rdf:Description rdf:ID="President">
			<foaf:Person>
				<xsl:variable name="id" select="generalInformation/president/@IDREF"/>
				<foaf:name>
					<xsl:value-of select="/document/effetiveMembers/member[@ID=$id]/name"/>
				</foaf:name>
			</foaf:Person>				
		</rdf:Description>
		<rdf:Description rdf:ID="MeetingWriter">
			<foaf:Person>
				<xsl:variable name="id" select="generalInformation/meetingWriter/@IDREF"/>
				<foaf:name>
					<xsl:value-of select="/document/effetiveMembers/member[@ID=$id]/name"/>
				</foaf:name>
			</foaf:Person>
		</rdf:Description>

		<xsl:for-each select="generalInformation/members/*">
			<rdf:Description rdf:ID="{name()}">
				<foaf:Person>
					<xsl:variable name="id" select="@IDREF"/>
					<foaf:name>
						<xsl:value-of select="/document/effetiveMembers/member[@ID=$id]/name"/>
					</foaf:name>
				</foaf:Person>
			</rdf:Description>
		</xsl:for-each>
	</xsl:template>

	<xsl:template name="topics">
		<xsl:for-each select="topics/topic">
			<rdf:Description rdf:ID="topic">
				<rdf:type rdf:resource="Topic"/>
				<rdf:indice>
					<xsl:value-of select="position()"/>
				</rdf:indice>
				<dc:title>
					<xsl:value-of select="topicTitle"/>
				</dc:title>
			</rdf:Description>
		</xsl:for-each>
	</xsl:template>

</xsl:stylesheet>