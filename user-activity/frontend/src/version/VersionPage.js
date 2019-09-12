import React, {Fragment} from 'react'

import VersionList from "./VersionList";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {fetchVersions} from "./versionDuck";

class VersionPage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {hasFetchVersions: false};
    }

    componentDidMount() {
        this.props.fetchVersions();
        this.setState({hasFetchVersions: true});
    }

    render() {
        const {versions} = this.props;
        const {hasFetchVersions} = this.state;
        return (
            <div>
                {hasFetchVersions && (
                    <Fragment>
                        <VersionList
                            versions={versions}
                        />
                    </Fragment>
                )}
            </div>
        )
    }
}

function mapStateToProps(state) {
    return {
        versions: state.version.versions,
    }
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators(
        {
            fetchVersions,
        },
        dispatch
    )
}

export default connect(
    mapStateToProps,
    mapDispatchToProps,
)(VersionPage)
