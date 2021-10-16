import ImgHeaderDangKy from "./ImgHeaderDangKy";

export default function Register() {
    return (
        <div>
            <div>
                <ImgHeaderDangKy/>
            </div>

            <div className="col-12 row mt-5" >
                <div className="col-4 offset-4" style={{ backgroundColor: 'rgb(246, 249, 249)' }}>
                    <form className="mx-2">
                        
                        <div className="form-group mt-3">
                            <label htmlFor="email">Email</label>
                            <input name="email" className="form-control"
                                id="email" autoComplete="off" type="email" />
                        </div>

                        <div className="form-group mt-3">
                            <label htmlFor="telephone">Số điện thoại</label>
                            <input name="telephone" className="form-control"
                                id="telephone" autoComplete="off" type="text" />
                        </div>

                        <div className="form-group mt-3">
                            <label htmlFor="password">Mật khẩu</label>
                            <input name="password" className="form-control"
                                id="password" autoComplete="off" type="password" />
                        </div>

                        <div className="form-group mt-3">
                            <label htmlFor="confirmPassword">Nhập lại mật khẩu</label>
                            <input name="confirmPassword" className="form-control"
                                id="confirmPassword" autoComplete="off" type="password" />
                        </div>

                        <div className="d-flex justify-content-center">
                            <button className="btn btn-info mt-3 mb-2" type="submit">Đăng ký</button>
                            <button className="btn btn-danger mt-3 mb-2 ms-3" type="reset">Hủy</button>
                        </div>

                    </form>
                </div>

            </div>
        </div>
    )
}