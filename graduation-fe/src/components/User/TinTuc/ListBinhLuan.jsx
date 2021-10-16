export default function ListBinhLuan() {
    return (
        <div className="col-12 row mb-2">

            {/* CONTENT1 */}
            <div className="card col-6 offset-3 border border-white">
                <hr className="ms-5 border border border-1"/>
                <div className="card-body ms-5">
                    <img src="https://mdbootstrap.com/img/new/fluid/nature/020.jpg"
                        className="img-fluid rounded-circle float-start me-1" alt="..."
                        style={{ width: '4rem', height: '4rem' }} />
                    <i className='fas fa-exclamation-circle 
                        d-flex justify-content-end' style={{ color: 'blue' }}></i>
                    <h5 className="card-title">
                        Sanchez
                    </h5>
                    <p className="card-text">
                        With supporting text below as a
                        natural lead-in to additional content.
                    </p>
                    <p className="card-text">
                        <small className="text-muted">06-10-2021</small>
                    </p>
                </div>
            </div>
            {/*END CONTENT1 */}

            {/* CONTENT2 */}
            <div className="card col-6 offset-3 border border-white">
                <div className="card-body ms-5">
                    <img src="https://mdbootstrap.com/img/new/fluid/nature/025.jpg"
                        className="img-fluid rounded-circle float-start me-1" alt="..."
                        style={{ width: '4rem', height: '4rem' }} />
                    <i className='fas fa-exclamation-circle 
                        d-flex justify-content-end' style={{ color: 'blue' }}></i>
                    <h5 className="card-title">
                        Alext
                    </h5>
                    <p className="card-text">
                        With supporting text below as a
                        natural lead-in to additional content.
                    </p>
                    <p className="card-text">
                        <small className="text-muted">06-10-2021</small>
                    </p>
                </div>
            </div>
            {/*END CONTENT2 */}

        </div>
    )
}