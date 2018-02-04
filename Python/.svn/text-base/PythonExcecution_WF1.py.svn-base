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
session.ip='10.210.32.229'
rhythmSrv = rhythm.Server()
session.rhythmSrv = rhythmSrv
session.LaunchAndConnect()

cwsobj=session.cwsObj
cwsApp = session.cwsApp

Job_Name = sys.argv[1].lower().replace(' ','')
jc = cws.JobCenter(cwsApp.Window_())
jl = jc.JobList
for row in range(0,jl.ItemCount()):
    temp = [i.lower().replace(" ", "") for i in jl.GetRowText(row)]
    if Job_Name in temp:
        if cwsobj.SelectJob(row) < 0:
            exit(-1)
        break
    
print "Job has been imported successfully"

cwsobj.Select("Actions->Properties...")
app=session.cwsApp
l=app.WaitForWindow("Job Properties",60)

m=jp.MEDIA(app)
material=m.PaperCatalog.ItemText()
print "Media value in CWS is "+(material)

c=jp.Color(app)
cmyk= c.CMYK.GetCheckState()
print "The value of cmyk is "+cmyk
if cmyk == 0:
    color_mode = "B&W"
elif cmyk == 1:
    color_mode = "Color"
else:
    print "Invalid value"
print "Color value in CWS is "+(color_mode)

j=jp.Layout(app)
l=j.Normal.Click()
Side=l.Duplex.ItemText()
print "Duplex Value in CWS is "+Side

f=jp.Finishing(app)
Fold=f.Fold.ItemText()
print "Fold Value in CWS is "+Fold
Staple=f.Stapling.ItemText()
print "Staples Value in CWS is "+Staple
punch=f.PunchHoles.ItemText()
print "Punch value in CWS is "+punch

ji=jp.JobInfo(app)
Copies=ji.Copies.ItemText()
numCopies= int (Copies)
numCopies=str(numCopies)
print "Number of copies value in CWS is "+numCopies
m =jp.MEDIA(app)
m.Print.Click()

from xlutils.copy import copy
import os.path
rb = open_workbook("C:\PaceProjects\SuiteWorkflow_TestComplete\PaceWorkflows\InputTestData\Epace_Data.xls",formatting_info=True)
r_sheet = rb.sheet_by_name('FieryData')
wb = copy(rb)
w_sheet=wb.get_sheet(5)
w_sheet.write(1,2,material)
w_sheet.write(2,2,color_mode)
w_sheet.write(3,2,Side)
w_sheet.write(4,2,Fold)
w_sheet.write(5,2,Staple)
w_sheet.write(6,2,punch)
w_sheet.write(7,2,numCopies)
wb.save("C:\PaceProjects\SuiteWorkflow_TestComplete\PaceWorkflows\InputTestData\Epace_Data.xls")

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
