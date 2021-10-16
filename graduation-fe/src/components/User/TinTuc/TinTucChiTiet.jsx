import BinhLuan from "./BinhLuan";
import ListBinhLuan from "./ListBinhLuan";
import ImageHeaderTinTuc from "./ImgHeaderTinTuc"
function TinTucChiTiet() {
    return (
        <div>
            {/* Header */}
            <div className="text-center">
                <ImageHeaderTinTuc />
            </div>
            {/* End Header */}

            <div className="col-12 row mt-5">

                {/* CATEGORY  */}
                <div className="col-md-3 ms-5">
                    <input className="form-control me-2" type="search"
                        placeholder="Tìm kiếm" aria-label="Search" style={{ width: '18rem' }} />

                    <h4 className="mt-2">Tin tức mới</h4>
                    <div className="card mt-2" style={{ width: '18rem' }}>
                        <ul className="list-group list-group-flush">

                            <li className="list-group-item">
                                <img src="https://mdbootstrap.com/img/new/fluid/nature/012.jpg"
                                    className="img-fluid rounded-circle float-start me-1" alt="..."
                                    style={{ width: '4rem', height: '4rem' }} />
                                <a className="fw-bold text-decoration-none text-dark" href="#">
                                    Điều trị tủy -Phòng ngừa RCT tốt hơn chữa bệnh
                                </a>
                            </li>
                            <li className="list-group-item">
                                <img src="https://mdbootstrap.com/img/new/fluid/nature/013.jpg"
                                    className="img-fluid rounded-circle float-start me-1" alt="..."
                                    style={{ width: '4rem', height: '4rem' }} />
                                <a className="fw-bold text-decoration-none text-dark" href="#">
                                    Nhổ răng – đích đến cuối cùng
                                </a>
                            </li>
                            <li className="list-group-item">
                                <img src="https://mdbootstrap.com/img/new/fluid/nature/014.jpg"
                                    className="img-fluid rounded-circle float-start me-1" alt="..."
                                    style={{ width: '4rem', height: '4rem' }} />
                                <a className="fw-bold text-decoration-none text-dark" href="#">
                                    Vệ sinh răng miệng – liều lượng và không
                                </a>
                            </li>

                        </ul>
                    </div>

                </div>
                {/* END CATEGORY */}


                {/* CONTENT  */}
                <div className="col-md-8" >

                    <div className="card mb-3 w-75" 
                        style={{ minHeight: '40rem',
                        minWidth:'40rem',
                        backgroundColor:'rgb(246, 249, 249)' }}>

                        <div className="card-body">
                            <img src="https://mdbootstrap.com/img/new/fluid/nature/012.jpg"
                                alt="..." style={{ width: '20rem', height: '20rem' }}
                                className="rounded float-start me-3" />
                            <h5 className="card-title"> Điều trị tủy -Phòng ngừa RCT tốt hơn chữa bệnh</h5>
                            <p className="card-text">
                                Lorem ipsum dolor sit amet, consectetur adipiscing elit,
                                sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
                                Ut enim ad minim veniam, quis nostrud exercitation
                                ullamco laboris nisi ut aliquip ex ea commodo consequat.
                                Duis aute irure dolor in reprehenderit
                                in voluptate velit esse cillum dolore eu fugiat nulla pariatur.
                                Excepteur sint occaecat cupidatat non proident, sunt in culpa qui
                                officia deserunt mollit anim id est laborum.

                                Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed
                                do eiusmod tempor incididunt ut labore et dolore magna aliqua.
                                Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris
                                nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in
                                reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla
                                pariatur. Excepteur sint occaecat cupidatat non proident,
                                sunt in culpa qui officia deserunt mollit anim id est laborum.

                                Lorem ipsum dolor sit amet, consectetur adipiscing elit,
                                sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
                                Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi
                                ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit
                                in voluptate velit esse cillum dolore eu fugiat nulla pariatur.
                                Excepteur sint occaecat cupidatat non proident, sunt in culpa qui
                                officia deserunt mollit anim id est laborum.
                            </p>
                            <p className="card-text"><small className="text-muted">Last updated 3 mins ago</small></p>
                            
                            <i className='far fa-thumbs-up fs-3'></i>
                            <i className='far fa-thumbs-down ms-2 fs-3'></i>
                        </div>

                       
                    </div>

                </div>
                {/*END CONTENT  */}
            </div>

            {/* COMMENT */}
            <BinhLuan/>
            {/* END COMMENT */}

            {/* LIST COMMENT */}
            <ListBinhLuan/>
            {/* END LIST COMMENT */}

        </div>
    )
}
export default TinTucChiTiet;