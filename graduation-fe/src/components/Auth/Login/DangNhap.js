import React from "react";

export default function DangNhap(){
    

    return(
      
        <div>

        <div className="d-flex justify-content-center">
            <div className="" style={{marginTop: '180px',width: '25rem',height: '20rem', border: '1px solid gray'}}>
                <form className="card-body text-center">
                    <h3>Đăng nhập</h3>
                    <div className="mt-2 text-start">
                        <label htmlFor="email" className="form-label ">Email:</label>
                        <input type="text" className="form-control" id="email" placeholder="an@gmail.com"/>
                    </div><br/>
                    <div className="text-start">
                    <label htmlFor="password" className="form-label text-start">Mật khẩu:</label>
                        <input type="password" className="form-control" id="password" />
                    </div><br/>
                    <button type="submit" className="btn btn-primary">Đăng nhập</button>             
                </form>
            </div>
        </div>
        </div>
       
    )
}