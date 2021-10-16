//form "hiển thị các thông tin bài viết"
import { makeStyles } from '@material-ui/core/styles';
function TinTuc() {

    //css title
    const useStyles = makeStyles((theme) => ({
        title: {
            fontSize : '2.2em',
            marginBottom: '0.2em',
            color: '#1c1c1c',
            fontWeight: 300,
            fontFamily: 'Roboto'
        },

    })); 
    const classes = useStyles();

    return (
        <div className="text-center pt-4 mt-5" style={{backgroundColor: 'rgb(246, 249, 249)',height:'700px'}}>
            {/* title */}
            <div>
                <h1 className={classes.title}>Tin tức nha khoa thế giới</h1>
                <p>Nụ cười làm tăng giá trị khuôn mặt của bạn!</p>
            </div>
            <br/><br/>
            <div id="carouselExampleControlsTinTuc" className="carousel slide" data-bs-ride="carousel">
                <div className="carousel-inner">

                    {/* CONTENT 1 */}
                    <div className="carousel-item active">
                        <div className="card-group mx-5">
                            <div className="card ms-3">
                                <img src="https://mdbootstrap.com/img/new/slides/020.jpg" className="card-img-top" alt="..." />
                                <div className="card-body">
                                    <h5 className="card-title">Card title</h5>
                                    <p className="card-text">This is a wider card with supporting text below as a natural lead-in to additional content.This content is a little bit longer.</p>
                                    <p className="card-text"><small className="text-muted">Last updated 3 mins ago</small></p>
                                </div>
                            </div>
                            <div className="card ms-3">
                                <img src="https://mdbootstrap.com/img/new/slides/020.jpg" className="card-img-top" alt="..." />
                                <div className="card-body">
                                    <h5 className="card-title">Card title</h5>
                                    <p className="card-text">This card has supporting text below as a natural lead-in to additional content.</p>
                                    <p className="card-text"><small className="text-muted">Last updated 3 mins ago</small></p>
                                </div>
                            </div>
                            <div className="card ms-3">
                                <img src="https://mdbootstrap.com/img/new/slides/020.jpg" className="card-img-top" alt="..." />
                                <div className="card-body">
                                    <h5 className="card-title">Card title</h5>
                                    <p className="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This card has even longer content than the first to show that equal height action.</p>
                                    <p className="card-text"><small className="text-muted">Last updated 3 mins ago</small></p>
                                </div>
                            </div>
                        </div>
                    </div>
                    {/* END CONTENT 1 */}


                    {/* CONTENT 2*/}
                    <div className="carousel-item">
                        <div className="card-group mx-5">
                            <div className="card ms-3">
                                <img src="https://mdbootstrap.com/img/new/slides/021.jpg" className="card-img-top" alt="..." />
                                <div className="card-body">
                                    <h5 className="card-title">Card title</h5>
                                    <p className="card-text">This is a wider card with supporting text below as a natural lead-in to additional content.This content is a little bit longer.</p>
                                    <p className="card-text"><small className="text-muted">Last updated 3 mins ago</small></p>
                                </div>
                            </div>
                            <div className="card ms-3">
                                <img src="https://mdbootstrap.com/img/new/slides/021.jpg" className="card-img-top" alt="..." />
                                <div className="card-body">
                                    <h5 className="card-title">Card title</h5>
                                    <p className="card-text">This card has supporting text below as a natural lead-in to additional content.</p>
                                    <p className="card-text"><small className="text-muted">Last updated 3 mins ago</small></p>
                                </div>
                            </div>
                            <div className="card ms-3">
                                <img src="https://mdbootstrap.com/img/new/slides/021.jpg" className="card-img-top" alt="..." />
                                <div className="card-body">
                                    <h5 className="card-title">Card title</h5>
                                    <p className="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This card has even longer content than the first to show that equal height action.</p>
                                    <p className="card-text"><small className="text-muted">Last updated 3 mins ago</small></p>
                                </div>
                            </div>
                        </div>
                    </div>
                    {/* END CONTENT 2*/}


                    {/* CONTENT 3 */}
                    <div className="carousel-item">
                        <div className="card-group mx-5">
                            <div className="card ms-3">
                                <img src="https://mdbootstrap.com/img/new/slides/022.jpg" className="card-img-top" alt="..." />
                                <div className="card-body">
                                    <h5 className="card-title">Card title</h5>
                                    <p className="card-text">This is a wider card with supporting text below as a natural lead-in to additional content.This content is a little bit longer.</p>
                                    <p className="card-text"><small className="text-muted">Last updated 3 mins ago</small></p>
                                </div>
                            </div>
                            <div className="card ms-3">
                                <img src="https://mdbootstrap.com/img/new/slides/022.jpg" className="card-img-top" alt="..." />
                                <div className="card-body">
                                    <h5 className="card-title">Card title</h5>
                                    <p className="card-text">This card has supporting text below as a natural lead-in to additional content.</p>
                                    <p className="card-text"><small className="text-muted">Last updated 3 mins ago</small></p>
                                </div>
                            </div>
                            <div className="card ms-3">
                                <img src="https://mdbootstrap.com/img/new/slides/022.jpg" className="card-img-top" alt="..." />
                                <div className="card-body">
                                    <h5 className="card-title">Card title</h5>
                                    <p className="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This card has even longer content than the first to show that equal height action.</p>
                                    <p className="card-text"><small className="text-muted">Last updated 3 mins ago</small></p>
                                </div>
                            </div>
                        </div>
                    </div>
                    {/*END CONTENT 3 */}

                </div>
                <button className="carousel-control-prev" type="button" data-bs-target="#carouselExampleControlsTinTuc" data-bs-slide="prev">
                    <span className="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span className="visually-hidden">Previous</span>
                </button>
                <button className="carousel-control-next" type="button" data-bs-target="#carouselExampleControlsTinTuc" data-bs-slide="next">
                    <span className="carousel-control-next-icon" aria-hidden="true"></span>
                    <span className="visually-hidden">Next</span>
                </button>
            </div>
        </div>
    )
}
export default TinTuc;