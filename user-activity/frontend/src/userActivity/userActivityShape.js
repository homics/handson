import PropTypes from 'prop-types';

export const userActivityShape = PropTypes.shape({
  username: PropTypes.string,
  activityType: PropTypes.string,
  activityDate: PropTypes.number,
});
