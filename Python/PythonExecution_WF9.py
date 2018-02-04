#-------------------------------------------------------------------------------
# Name:        module1
# Purpose:
#
# Author:      nageshbe
#
# Created:     12/11/2014
# Copyright:   (c) nageshbe 2014
# Licence:     <your licence>
#-------------------------------------------------------------------------------

import sys
from tempfile import TemporaryFile
from xlwt import Workbook
import pyodbc
import subprocess
from xlrd import open_workbook
from xlwt import easyxf
from time import sleep


sys.path.append("c:\clientapp_test")
from clientapp import *
from clientapp import cws
session = GetSession()
session.ip='10.210.38.41'
rhythmSrv = rhythm.Server()
session.rhythmSrv = rhythmSrv
session.LaunchAndConnect()

cwsobj=session.cwsObj
cwsApp = session.cwsApp
##cwsobj.ImportJob("Bignum1.pdf")
print "Job has been imported successfully"



##cwsobj.SelectJob("Bignum1.pdf")

Job_Name = sys.argv[1].lower().replace(' ','')
jc = cws.JobCenter(cwsApp.Window_())
jl = jc.JobList
for row in range(0,jl.ItemCount()):
    temp = [i.lower().replace(" ", "") for i in jl.GetRowText(row)]
    if Job_Name in temp:
        if cwsobj.SelectJob(row) < 0:
            exit(-1)
        break


cwsobj.Select("Actions->Properties...")
app=session.cwsApp
l=app.WaitForWindow("Job Properties",60)
print l
##jp=jp.Media(session.cwsApp)
m =jp.MEDIA(app)


print (m.PaperSize.ItemText())
pc=m.PaperCatalog.ItemText()
print (pc)
print "Media value in CWS is "+(pc)

##ps=m.InputTray.ItemText()
##print (ps)
##mdc=m.PaperColor.ItemText()
##print (mdc)
##mdw=m.MediaWeight.ItemText()
##print (mdw)

##mdt=m.MediaType.ItemText()
##print (mdt)
##jp.Color(app)
c=jp.Color(app)
##cmyk= c.CMYK.GetCheckState()
##print "The value of cmyk is ",cmyk

##if cmyk == 0:
##    color_mode = "B&W"
##elif cmyk == 1:
##    color_mode = "Color"
##else:
##    print "Invalid value"

##print "Color value in CWS is "+(cmyk)
j=jp.Layout(app)

l=j.Normal.Click()
Side=l.Duplex.ItemText()
print "Duplex Value in CWS is "+Side

f=jp.Finishing(app)
print(f.SorterMode.ItemText())
colate= f.SorterMode.ItemText()
##Print "Collate value in CWS is "+colate
f.FoldStyle.ItemText()
Fold=f.FoldStyle.ItemText()
print "Fold Value in CWS is "+Fold
Staple=f.Stapling.ItemText()
print Staple
print "Staples Value in CWS is "+Staple
##punch=f.HolePunch.ItemText()
punch=f.PunchHoles.ItemText()
print "Punch value in CWS is "+punch


ji=jp.JobInfo(app)
print(ji.Copies.ItemText())
Copies=ji.Copies.ItemText()
numCopies= int (Copies)
numCopies=str(numCopies)
m =jp.MEDIA(app)
m.Print.Click()
##print (sys.argv[2])
##testcolum=sys.argv[2]

##cnxn=pyodbc.connect("""Driver={Microsoft Excel Driver (*.xls)};Dbq=C:\\Integration-Lab\\DSF_ResponsiveUI.xls; readonly=0""", autocommit=True);
##curs = cnxn.cursor();
####curs.execute("select TC1 from [FieryData$] where ParameterName = 'DSF_Fold'");
##curs.execute("update [FieryData$] set testcases=%s where ParameterName = 'CWS_Copies'"% copy);
####curs.execute("update [FieryData$] set testcases=%s where ParameterName = 'CWS_Media'"% pc)
##curs.execute("update [FieryData$] set testcases=? where ParameterName = 'CWS_Collate'", colate);
##curs.execute("update [FieryData$] set testcases=%s where ParameterName = 'CWS_Color'"% cmyk);
##
##subprocess.call(['D:\\SaveExcel.vbs'])
##subprocess.call("cscript D:\\SaveExcel.vbs")
##curs.close()
from xlutils.copy import copy
import os.path
rb = open_workbook("C:\workspace\EPace_Merged\InputTestData\Epace_Data.xls",formatting_info=True)
r_sheet = rb.sheet_by_name('FieryCentralValue')
print "The contents are %s,%s"%(pc,colate)

wb = copy(rb)
w_sheet=wb.get_sheet(28)
k=int(sys.argv[2])
##w_sheet.write(3,k,color_mode)
w_sheet.write(4,k,numCopies)
w_sheet.write(1,k,pc)
w_sheet.write(2,k,colate)
w_sheet.write(5,k,Staple)
w_sheet.write(6,k,Side)
w_sheet.write(7,k,Fold)
w_sheet.write(8,k,punch)
wb.save("C:\workspace\EPace_Merged\InputTestData\Epace_Data.xls")
##wb.save("D:\\DemoExcel" + os.path.splitext("D:\\DemoExcel.xls")[-1])


from clientapp import cws
sleep(30)
cwsobj.GoToPrintedJobs()
Job_Name = sys.argv[1].lower().replace(' ','')
jc = cws.JobCenter(cwsApp.Window_())
jl = jc.JobList
print ">>>> count", jl.ItemCount()
for row in range(0,jl.ItemCount()):
    temp = [i.lower().replace(" ", "") for i in jl.GetRowText(row)]


    if Job_Name in temp:
        print "Job is present in CWS printed List"
        if cwsobj.SelectJob(row) < 0:
            print "Job not found in printed list"
            exit(-1)
        break
sleep(20)
cwsobj.GoToHeldJobs()