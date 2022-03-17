<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" version="4.01" encoding="UTF-8"/>

<xsl:key name="members" match="member" use="@ID"/>
<xsl:key name="guest" match="guest" use="@ID"/>

<xsl:template match="/document">
    <html>
        <head>
            <link rel="stylesheet" type="text/css" href="Trabalho2.css"/>
            <xsl:call-template name="validations">
                
            </xsl:call-template>
        </head>
        <body>
            
            <h1 class="divStyle">
                Minutes Document
            </h1>
            <xsl:call-template name="introduction"/>
            <div id="flexDiv">
                <div class="divStyle divWidth">
                    <xsl:apply-templates select="effectiveMembers"/>
                </div>
                <div class="divStyle divWidth">
                    <xsl:call-template name="meetingIndexes"/>
                </div>
            </div>
            <xsl:apply-templates select="minutes/meeting"/>
        </body>
    </html>
</xsl:template>

<xsl:template name="introduction">
    <div id="introduction" class="divStyle">
        This document shows the minutes of our campany for the Organs: 
        <xsl:for-each select="minutes/meeting/generalInformation/organName/text()">
            <xsl:if test="not(preceding::text() = .)">
                &#160;<xsl:value-of select="."/>&#160;
            </xsl:if>
        </xsl:for-each>
    </div>
</xsl:template>

<xsl:template match="effectiveMembers">
    <h2>Member List:</h2>
    <ul>
        <xsl:for-each select="member">
            <li>  
                <xsl:if test="title != ''">
                    <xsl:value-of select="title"/><xsl:text> </xsl:text>
                </xsl:if>
                <xsl:value-of select="name"/>
            </li>
        </xsl:for-each>
    </ul>
</xsl:template>

<xsl:template name="meetingIndexes">
    <h2>Meetings:</h2>
    <ul>
        <xsl:for-each select="minutes/meeting">
            <a href="#{generate-id()}">
                <li>
                    Meeting 
                    <xsl:value-of select="generalInformation/date"/>,
                    <xsl:value-of select="generalInformation/schedule/startingTime/hour"
                        />:<xsl:value-of select="generalInformation/schedule/startingTime/minute"/>
                </li>
            </a>
        </xsl:for-each>
    </ul>
</xsl:template>

<xsl:template match="meeting">
    <xsl:for-each select=".">
        <div class="divStyle">
            <a name="{generate-id()}"></a>
            <xsl:apply-templates select="generalInformation" mode="visualisation"/>
            <xsl:apply-templates select="generalInformation/meetingMembers" mode="visualisation"/>
            <xsl:apply-templates select="topics" mode="visualisation"/>
        </div>
    </xsl:for-each>
</xsl:template>

<xsl:template match="generalInformation" mode="visualisation">
    <div id="generalInformationDiv">
        <div class="headerStyle">
            <xsl:value-of select="date"/>
        </div>
        <xsl:if test="meetingType != ''">
            <div class="headerStyle">
                <xsl:value-of select="meetingType"/>
            </div>
        </xsl:if>
        <div class="headerStyle">
            President: 
            <xsl:value-of select="key('members',president/@IDREF)/name"/>
            <xsl:value-of select="key('guest', @IDREF)/name"/>
        </div>
        <div class="headerStyle">
            Writer: 
            <xsl:value-of select="key('members',meetingWriter/@IDREF)/name"/>
            <xsl:value-of select="key('guest', @IDREF)/name"/>
        </div>
        <div id="headerDiv">
            &#160;
        </div>
    </div>
</xsl:template>

<xsl:template match="meetingMembers" mode="visualisation">
    <div class="innerDivStyle" id="meetingMembersDiv">
        <span class="innerDivStyle" id="meetingMembersSpan">
            Meeting Members
        </span>
        <ul>
            <xsl:for-each select="meetingMember">
                <li>
                    <xsl:if test="key('members',@IDREF)/title != ''">
                        <xsl:value-of select="key('members',@IDREF)/title"/>
                        <xsl:text> </xsl:text>
                    </xsl:if>
                    <xsl:value-of select="key('members',@IDREF)/name"/>
                    <xsl:if test="justification != ''">
                        (Absent - <xsl:value-of select="justification"/>)
                    </xsl:if>
                </li>
            </xsl:for-each>
            <xsl:for-each select="guest">
                <li>
                    Guest:
                    <xsl:if test="title != ''">
                        <xsl:value-of select="title"/>
                        <xsl:text> </xsl:text>
                    </xsl:if>
                    <xsl:value-of select="name"/>
                </li>
            </xsl:for-each>
        </ul>
    </div>
</xsl:template>

<xsl:template match="topics" mode="visualisation">
    <xsl:for-each select="topic">
        <div class="innerDivStyle" id="topicsDiv">
            <xsl:if test="topicTitle !=''">
                <span class="innerDivStyle" id="topicsSpan">
                    <xsl:value-of select="topicTitle"/>
                </span>
            </xsl:if>
            <div id="topicsDiv2">
                <xsl:apply-templates select="discussion" mode="visualisation"/>
                <xsl:apply-templates select="voting" mode="visualisation"/>
            </div>
        </div>
    </xsl:for-each>
</xsl:template>

<xsl:template match="discussion" mode="visualisation">
    <xsl:apply-templates mode="visualisation"/>
</xsl:template>

<xsl:template match="section" mode="visualisation">
    <div>
        <br/>
        <span class="sectionNumeration">
            <xsl:number value="count(preceding-sibling::*[name()='section'])+1"/>.
        </span>
        <xsl:apply-templates mode="visualisation"/>
        <br/>
    </div>
</xsl:template>

<xsl:template match="subsection" mode="visualisation">
    <div id="subsection">
        <br/>
        <span class="sectionNumeration">
            <xsl:number value="count(ancestor::*/preceding-sibling::*[name()='section'])+1"
                />.<xsl:number value="count(preceding-sibling::*[name()='subsection'])+1"/>
        </span>
        <xsl:apply-templates mode="visualisation"/>
        <br/>
    </div>
</xsl:template>

<xsl:template match="paragraph" mode="visualisation">
    <br/>
    <xsl:apply-templates mode="visualisation"/>
    <br/>
</xsl:template>

<xsl:template match="entry" mode="visualisation">
    [
    <xsl:value-of select="key('members',@IDREF)/name"/>
    <xsl:value-of select="key('guest', @IDREF)/name"/>
    entered the Meeting ]
</xsl:template>

<xsl:template match="exit" mode="visualisation">
    [
    <xsl:value-of select="key('members',@IDREF)/name"/>
    <xsl:value-of select="key('guest', @IDREF)/name"/>
    left the Meeting ]
</xsl:template>

<xsl:template match="speaker" mode="visualisation">
    [ spoke
    <xsl:value-of select="key('members',@IDREF)/name"/>
    <xsl:value-of select="key('guest', @IDREF)/name"/>
    ]
</xsl:template>

<xsl:template match="voting" mode="visualisation">
    <table>
        <tr>
            <th colspan="2">
                <xsl:value-of select="subject"/>
            </th>
        </tr>
        <xsl:choose>
            <xsl:when test="name(*[2])='alternative'">
                <xsl:for-each select="alternative/*">
                    <tr>
                        <th>
                            <xsl:value-of select="@name"/>:
                        </th>
                        <td>
                            <xsl:number value="count(voter)"/>
                        </td>
                    </tr>
                </xsl:for-each>
            </xsl:when>
            <xsl:when test="name(*[2])='motion'">
                <xsl:for-each select="motion/*">
                    <tr>
                        <th>
                            <xsl:value-of select="name(.)"/>:
                        </th>
                        <td>
                            <xsl:number value="count(voter)"/>
                        </td>
                    </tr>
                </xsl:for-each>
            </xsl:when>
        </xsl:choose>
    </table>
</xsl:template>


<!--..........................Validations...................................-->


<xsl:template name="validations">
    <xsl:apply-templates select="minutes/meeting/generalInformation/schedule" mode="validation"/>
    <xsl:apply-templates select="minutes/meeting/topics/topic/discussion" mode="validation"/>
    <xsl:apply-templates select="minutes/meeting/generalInformation" mode="validation"/>
    <xsl:apply-templates select="minutes/meeting/generalInformation/meetingMembers" mode="validation"/>
    <xsl:apply-templates select="minutes/meeting/topics/topic/voting" mode="validation"/>
</xsl:template>

<xsl:template match="schedule" mode="validation">
    <xsl:if test="startingTime/hour &gt; endingTime/hour or 
        (startingTime/hour = endingTime/hour and startingTime/minute &gt; endingTime/minute)">
        <xsl:message terminate="no">
                Error: The meeting is starting before ending.
        </xsl:message>
    </xsl:if>  
</xsl:template>

<xsl:template match="discussion" mode="validation">
    <xsl:for-each select="descendant::entry">
        <xsl:if test="ancestor::meeting/generalInformation/meetingMembers/meetingMember/@IDREF = @IDREF or 
            ancestor::meeting/generalInformation/meetingMembers/guest/@ID = @IDREF">
            <xsl:message terminate="no">
                Error: <xsl:value-of select="key('members',@IDREF)/name"
                        /><xsl:value-of select="key('guest',@IDREF)/name"
                        /> entered the meeting while being there since the beginning.          
            </xsl:message>
        </xsl:if>
    </xsl:for-each>
    
    <xsl:for-each select="descendant::exit">
        <xsl:if test="not(ancestor::meeting/generalInformation/meetingMembers/meetingMember/@IDREF = @IDREF) and 
            not(ancestor::meeting/generalInformation/meetingMembers/guest/@ID = @IDREF)">
            <xsl:message terminate="no">
                Error: <xsl:value-of select="key('members',@IDREF)/name"
                        /><xsl:value-of select="key('guest',@IDREF)/name"
                        /> left the meeting while not even being present.
            </xsl:message>
        </xsl:if>
    </xsl:for-each>
    
    <xsl:for-each select="descendant::speaker">
        <xsl:variable name="idSpeaker" select="@IDREF"/>
        <xsl:if test="not(ancestor::meeting/generalInformation/meetingMembers/meetingMember/@IDREF = $idSpeaker) and 
            not(ancestor::meeting/generalInformation/meetingMembers/guest/@ID = $idSpeaker)">
            <xsl:message terminate="no">
                Error: <xsl:value-of select="key('members',$idSpeaker)/name"
                        /><xsl:value-of select="key('guest',$idSpeaker)/name"
                        /> spoke at the meeting while not even being present.
            </xsl:message>
        </xsl:if>
    </xsl:for-each>
</xsl:template>

<xsl:template match="voting" mode="validation">
    <xsl:for-each select="*[2]/*/voter">
        <xsl:if test="../following-sibling::*/voter/@IDREF = @IDREF or 
                      following-sibling::*/@IDREF = @IDREF">
            <xsl:message terminate="no">
                Error: <xsl:value-of select="key('members',@IDREF)/name"
                    /><xsl:value-of select="key('guest',@IDREF)/name"
                    /> voted more than once at the subject "<xsl:value-of select="ancestor::voting/subject"/>".
            </xsl:message>
        </xsl:if>
        
        <xsl:if test="not(ancestor::meeting/generalInformation/meetingMembers/meetingMember/@IDREF = @IDREF) and 
            not(ancestor::meeting/generalInformation/meetingMembers/guest/@ID = @IDREF)">
            <xsl:message terminate="no">
                Error: <xsl:value-of select="key('members',@IDREF)/name"
                    /><xsl:value-of select="key('guest',@IDREF)/name"
                    /> was not present and still voted at the subject "<xsl:value-of select="ancestor::voting/subject"/>".
            </xsl:message>
        </xsl:if>
    </xsl:for-each>
</xsl:template>

<xsl:template match="meetingMembers" mode="validation">
    <xsl:for-each select="*">
        <xsl:choose>
            <xsl:when test="name(.) = 'meetingMember'">
                <xsl:call-template name="memberVotingValidation">
                    <xsl:with-param name="id" select="@IDREF"/>
                </xsl:call-template>
            </xsl:when>
            <xsl:when test="name(.) = 'guest'">
                <xsl:call-template name="memberVotingValidation">
                    <xsl:with-param name="id" select="@ID"/>
                </xsl:call-template>
            </xsl:when>
        </xsl:choose>
    </xsl:for-each>
</xsl:template>

<xsl:template name="memberVotingValidation">
    <xsl:param name="id"/>
    <xsl:for-each select="ancestor::meeting/topics/topic/voting">
            <xsl:choose>
                <xsl:when test="name(*[2])='alternative'">
                   <xsl:if test="not(alternative/*/voter/@IDREF = $id)">
                        <xsl:message terminate="no">
                Error: <xsl:value-of select="key('members',$id)/name"
                        /><xsl:value-of select="key('guest',$id)/name"
                        /> was present but did not vote at the subject "<xsl:value-of select="subject"/>".
                        </xsl:message>
                    </xsl:if>   
                </xsl:when>
                <xsl:when test="name(*[2])='motion'">
                    <xsl:if test="not(motion/*/voter/@IDREF = $id)">
                        <xsl:message terminate="no">
                Error: <xsl:value-of select="key('members',$id)/name"
                        /><xsl:value-of select="key('guest',$id)/name"
                        /> was present but did not vote at the subject "<xsl:value-of select="subject"/>".
                        </xsl:message>
                    </xsl:if>  
                </xsl:when>
            </xsl:choose>
        </xsl:for-each>
</xsl:template>

<xsl:template match="generalInformation" mode="validation">
    <xsl:if test="not(meetingMembers/meetingMember/@IDREF = president/@IDREF) and 
        not(meetingMembers/guest/@ID = president/@IDREF)">
        <xsl:message terminate="no">
                Error: The president is not present.
        </xsl:message>
    </xsl:if>
    
    <xsl:if test="not(meetingMembers/meetingMember/@IDREF = meetingWriter/@IDREF) and 
        not(meetingMembers/guest/@ID = meetingWriter/@IDREF)">
        <xsl:message terminate="no">
                Error: The meeting writer is not present.
        </xsl:message>
    </xsl:if>
</xsl:template>

</xsl:stylesheet>