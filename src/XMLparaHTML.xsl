<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="pessoas">
		<h2>Pessoas</h2>
		<p>Tipo: <xsl:value-of select="@tipo"/></p>
		<xsl:apply-templates select="pessoa"/>
	</xsl:template>
	<xsl:template match="pessoa">
		<p><xsl:value-of select="nome"/></p>
		<p><xsl:value-of select="idade"/></p>
		<hr/>
	</xsl:template>
</xsl:stylesheet>