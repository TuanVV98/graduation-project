export default function InfoKhachHang() {
    return (
        <div>
            <div className="row border border-dark mt-5 mb-5" style={{height:'260px'}}>
                <h3>Thông tin khách hàng</h3>
                
                <div className="col-md-2 ms-5">
                    <div >
                        <label htmlFor="exampleFormControlInput1" className="form-label">Hình ảnh:</label><br/>
                        <img id="exampleFormControlInput1" 
                        src="https://mdbootstrap.com/img/new/fluid/nature/019.jpg" 
                        className="card-img" alt="no img" 
                        style={{height: '150px',width: '180px'}} />
                    </div>
                </div>

                <div className="col-md-3 mb-3">
                    <div className="form-group mt-3">
                        <label htmlFor="id">Id</label>
                        <input name="id" className="form-control"
                            id="id" autoComplete="off" defaultValue="1"
                            type="text" readOnly />
                    </div>
                    <div className="form-group mt-3">
                        <label htmlFor="name">Họ và tên</label>
                        <input name="name" className="form-control"
                            id="name" autoComplete="off" defaultValue="Nguyễn Văn Đạt"
                            type="text" readOnly />
                    </div>                
                </div>

                <div className="col-md-3">
                    <div className="form-group mt-3 ">
                        <label htmlFor="birthday">Ngày sinh</label>
                        <input name="birthday" className="form-control" defaultValue="07/10/2000"
                            id="birthday" autoComplete="off" type="text" readOnly />
                    </div>
                    <div className="form-group mt-3">
                        <label htmlFor="email">Điện thoại</label>
                        <input name="email" className="form-control"
                            defaultValue="0123456789"
                            id="email" autoComplete="off" type="email" readOnly />
                    </div>
                </div>
                
                <div className="mt-3 col-md-3">
                    <label htmlFor="exp">Mô tả</label>
                    <textarea name="exp" className="form-control"
                        defaultValue="Tôi bị đau răng"
                        id="exp" autoComplete="off" type="text" rows="4" readOnly />
                </div>
                

                
            </div>
        </div>
    )
}