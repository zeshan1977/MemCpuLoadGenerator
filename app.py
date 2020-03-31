from flask import Flask, render_template, request
from flask_sqlalchemy import SQLAlchemy
app=Flask(__name__)
ENV= 'dev'
if ENV =='dev':
    app.debug= True
    app.config['SQL_ALCHEMY_DATABASE_URI']= 'postgresq://postgresql:hammed00@localhost/testdb'
else:
    app.debug =False
    app.config['SQL_ALCHEMY_DATABASE_URI']= ''
    
    
app.config['SQLALCHEMY_TRACK_MODIFICATIONS']= False



database= SQLAlchemy(app)

class svpco_modeldatabase(database.Model):
    __tablename__='feedback'
    id=database.Column(database.Integer,primary_key=True)
    customer=database.Column(database.String(200),unique=True)
        

@app.route('/')
#What went bang!

def index():
    return render_template('index.html')

@app.route('/submit',methods=['POST'])
def submit():
    customer=request.form['customer']
    dealer=request.form['dealer']
    rating=request.form['rating']
    comments=request.form['comments']
    print ("submit===========>:",customer,dealer,rating,comments)
    return render_template('success.html')


if __name__ == '__main__':
  
    app.run()
    