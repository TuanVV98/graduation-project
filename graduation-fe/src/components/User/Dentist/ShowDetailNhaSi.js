//hiển thị thông tin chi tiết của nha sĩ
import ImgHeaderNhaSi from "./ImgHeaderNhaSi";
function ShowDetailNhaSi() {
    return (
        <div>
            <div className="text-center">
                <ImgHeaderNhaSi />
            </div>

            <div className="col-12 row mt-2">

                {/* CONNTENT1 */}
                <div className="card mb-3 col-6 offset-3" style={{ maxWidth: '50rem' }}>
                    <div className="row g-0">
                        <div className="col-md-4">
                            <img src="https://mdbootstrap.com/img/new/fluid/nature/018.jpg"
                                className="img-fluid rounded-start" alt="..." style={{ width: '20rem', height: '20rem' }} />
                        </div>
                        <div className="col-md-8">
                            <div className="card-body">
                                <h5 className="card-title fs-4">Văn Tuấn</h5>
                                <p className="card-text"><span className="fw-bold">Ngày sinh:</span> 27-12-1991</p>
                                <p className="card-text"><span className="fw-bold">Thông tin liên hệ:</span> vantuan@gmail.com</p>
                                <p className="card-text fw-bold">Kinh nghiệm:</p>
                                <p className="card-text">
                                    <textarea className="form-control"
                                        id="exampleFormControlTextarea1" rows="4"
                                        readOnly style={{ backgroundColor: 'white' }}
                                        defaultValue='Blinken, who spoke French throughout the interview,
                                    said he understood the sense of betrayal and that Americans realize
                                    they "could have -- we should have -- done better, in terms of communication.
                                    " And he admitted that, "above all,
                                    we sometimes tend to take for granted a relationship as important and deep as
                                    the one between France and the United States."'>
                                    </textarea>
                                </p>
                                <p className="card-text"><small className="text-muted">Last updated 3 mins ago</small></p>
                            </div>
                        </div>
                    </div>
                </div>
                {/* END CONTENT1 */}

                {/* CONNTENT2 */}
                <div className="card mb-3 col-6 offset-3" style={{ maxWidth: '50rem' }}>
                    <div className="row g-0">
                        <div className="col-md-4">
                            <img src="https://mdbootstrap.com/img/new/fluid/nature/019.jpg"
                                className="img-fluid rounded-start" alt="..." style={{ width: '20rem', height: '20rem' }} />
                        </div>
                        <div className="col-md-8">
                            <div className="card-body">
                                <h5 className="card-title fs-4">Depay</h5>
                                <p className="card-text"><span className="fw-bold">Ngày sinh:</span> 11-11-1972</p>
                                <p className="card-text"><span className="fw-bold">Thông tin liên hệ:</span> depay@gmail.com</p>
                                <p className="card-text fw-bold">Kinh nghiệm:</p>
                                <p className="card-text">
                                    <textarea className="form-control"
                                        id="exampleFormControlTextarea1" rows="4"
                                        readOnly style={{ backgroundColor: 'white' }}
                                        defaultValue='Blinken, who spoke French throughout the interview,
                                    said he understood the sense of betrayal and that Americans realize
                                    they "could have -- we should have -- done better, in terms of communication.
                                    " And he admitted that, "above all,
                                    we sometimes tend to take for granted a relationship as important and deep as
                                    the one between France and the United States."'>
                                    </textarea>

                                </p>
                                <p className="card-text"><small className="text-muted">Last updated 3 mins ago</small></p>
                            </div>
                        </div>
                    </div>
                </div>
                {/* END CONTENT2 */}

                {/* CONNTENT3 */}
                <div className="card mb-3 col-6 offset-3" style={{ maxWidth: '50rem' }}>
                    <div className="row g-0">
                        <div className="col-md-4">
                            <img src="https://mdbootstrap.com/img/new/fluid/nature/020.jpg"
                                className="img-fluid rounded-start" alt="..." style={{ width: '20rem', height: '20rem' }} />
                        </div>
                        <div className="col-md-8">
                            <div className="card-body">
                                <h5 className="card-title fs-4">Marcus</h5>
                                <p className="card-text"><span className="fw-bold">Ngày sinh:</span> 01-02-1980</p>
                                <p className="card-text"><span className="fw-bold">Thông tin liên hệ:</span> marcus@gmail.com</p>
                                <p className="card-text fw-bold">Kinh nghiệm:</p>
                                <p className="card-text">
                                    <textarea className="form-control"
                                        id="exampleFormControlTextarea1" rows="4"
                                        readOnly style={{ backgroundColor: 'white' }}
                                        defaultValue='Blinken, who spoke French throughout the interview,
                                    said he understood the sense of betrayal and that Americans realize
                                    they "could have -- we should have -- done better, in terms of communication.
                                    " And he admitted that, "above all,
                                    we sometimes tend to take for granted a relationship as important and deep as
                                    the one between France and the United States."'>
                                    </textarea>
                                </p>
                                <p className="card-text"><small className="text-muted">Last updated 3 mins ago</small></p>
                            </div>
                        </div>
                    </div>
                </div>
                {/* END CONTENT3 */}


                {/* PAGINATION */}
                <div className="mt-2">
                    <ul className="pagination justify-content-center">
                        <li className="page-item" >
                            <button className="page-link">
                                <i className='fas fa-angle-left'></i>
                            </button>
                        </li>
                        <li className="page-item">
                            <button className="page-link">
                                <i className='fas fa-angle-double-left'></i>
                            </button>
                        </li>
                        <li className="page-item">
                            <button className="page-link">2/5</button>
                        </li>
                        <li className="page-item">
                            <button className="page-link">
                                <i className='fas fa-angle-double-right'></i>
                            </button>
                        </li>
                        <li className="page-item">
                            <button className="page-link">
                                <i className='fas fa-angle-right'></i>
                            </button>
                        </li>
                    </ul>
                </div>
                {/* END PAGINATION */}
            </div>
        </div>
    )
}
export default ShowDetailNhaSi;