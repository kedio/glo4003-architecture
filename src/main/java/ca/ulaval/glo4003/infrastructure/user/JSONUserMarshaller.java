package ca.ulaval.glo4003.infrastructure.user;

import ca.ulaval.glo4003.domain.persistence.UserMarshaller;
import ca.ulaval.glo4003.domain.user.User;
import ca.ulaval.glo4003.infrastructure.persistence.JSONMarshaller;

public class JSONUserMarshaller extends JSONMarshaller<User> implements UserMarshaller {

    public JSONUserMarshaller() {
        super(User.class);
    }
}
