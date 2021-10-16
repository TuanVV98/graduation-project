import React from 'react';
import TextField from '@material-ui/core/TextField';

export default function ThongTinBacSi(){
    


    return(
        <div className="pt-4  text-center" style={{backgroundColor: 'rgb(246, 249, 249)',height:'900px'}}> 
            <h3 className="">QUẢN LÝ THÔNG TIN CÁ NHÂN</h3><br/>
            <form>
                <div className="row text-start">
                <div className="col-sm-3 offset-2"> 

                    <div>
                        <label htmlFor="exampleFormControlInput1" className="form-label">Email:</label>
                        <input type="email" className="form-control" id="exampleFormControlInput1" placeholder="an@gmail.com" readOnly/>
                    </div><br/>
                    
                    <div>
                        <label htmlFor="1" className="form-label">Căn cước công dân:</label>
                        <input type="text" className="form-control" id="1" placeholder="00120101010205"/>
                    </div><br/>

                    <div>
                        <label htmlFor="2" className="form-label">Họ và tên:</label>
                        <input type="text" className="form-control" id="2" placeholder="Nguyen Van An"/>
                    </div>

                    <div className="mt-4">
                        <label htmlFor="date" className="form-label">Ngày sinh:</label><br/>
                        <TextField
                            className=""
                            id="date"
                            type="date"
                            defaultValue="2017-05-24"
                        />
                    </div><br/>

                    <div className="mt-2">
                        <label >Giới tính:</label><br/>
                        <div className="mt-2">
                        <input type="radio" id="nam" name="fav_language" value="Nam" defaultChecked/>
                        <label htmlFor="nam">Nam</label>
                        <input type="radio" id="nu" name="fav_language" value="Nữ"/>
                        <label htmlFor="nu">Nữ</label>
                        </div>
                    </div><br/>


                    <div className="mt-2">
                        <label htmlFor="exampleFormControlTextarea1" className="form-label">Kinh nghiệm:</label>
                        <textarea className="form-control" id="exampleFormControlTextarea1" rows="7"></textarea>
                    </div><br/>

                </div>
                
                <div className="col-sm-3 offset-2">
                
                <div>
                        <label htmlFor="exampleFormControlInput1" className="form-label">Số điện thoại:</label>
                        <input type="text" className="form-control" id="exampleFormControlInput1" placeholder="0123456789"/>
                </div><br/>

                <div>
                    <label htmlFor="exampleFormControlInput1" className="form-label">Tỉnh/Thành Phố:</label>
                    <select className="form-select" aria-label="Default select example">
                        <option defaultChecked>Chọn thành phố</option>
                        <option value="1">Hà Nội</option>
                        <option value="2">Hồ Chí Minh</option>
                        <option value="3">...</option>
                    </select>
                    </div><br/>

                    <div>
                    <label htmlFor="exampleFormControlInput1" className="form-label">Quận/Huyện:</label>
                    <select className="form-select" aria-label="Default select example">
                        <option defaultChecked>Chọn huyện</option>
                        <option value="1">Thạch Thất</option>
                        <option value="2">Quốc Oai</option>
                        <option value="3">...</option>
                    </select>
                    </div><br/>
                

                <div>
                    <label htmlFor="exampleFormControlInput1" className="form-label">Phường/Xã:</label>
                    <select className="form-select" aria-label="Default select example" id="exampleFormControlInput1">
                        <option defaultChecked>Chọn xã</option>
                        <option value="1" >Thạch Xá</option>
                        <option value="2">Cần Kiệm</option>
                        <option value="3">...</option>
                    </select>
                </div><br/>

                <div>
                    <label htmlFor="formFile" className="form-label">Chọn ảnh:</label>
                    <input className="form-control" type="file" id="formFile"/>
                </div><br/>
                
                <div>
                    <label htmlFor="exampleFormControlInput1" className="form-label">Hình ảnh:</label><br/>
                    <img id="exampleFormControlInput1" src="https://mdbootstrap.com/img/new/fluid/nature/018.jpg" className="card-img" alt="no img" style={{height: '170px',width: '150px'}}></img>
                </div>
                
                </div> 
                </div>
                <br/>
                <div>
                    <button type="button" className="btn btn-primary">Cập nhật</button>
                </div>
            </form>

            </div>
       
    )
}