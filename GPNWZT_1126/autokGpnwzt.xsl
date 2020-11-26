<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema" version="1.0">
        <xsl:template match="auto">
            <xsl:value-of select="@rsz"/>
        </xsl:template>
</xsl:stylesheet>