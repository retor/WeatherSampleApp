package pro.retor.weathersampleapp.impl.di.data.db;

import dagger.Module;
import pro.retor.weathersampleapp.impl.di.application.MainAppModule;

/**
 * Created by retor on 31.05.2016.
 */
@Module(includes = MainAppModule.class)
public class DBModule {
/*    @Provides
    @Singleton
    public SQLiteOpenHelper providesSQLOpenHelper(DBHelper helper) {
        return helper;
    }

    @Provides
    @Singleton
    public StorIOSQLite providesStorIOSQLite(SQLiteOpenHelper helper) {
        return DefaultStorIOSQLite.builder()
                .sqliteOpenHelper(helper)
                .addTypeMapping(DBProfileInfo.class, SQLiteTypeMapping.<DBProfileInfo>builder()
                        .putResolver(new DBProfileInfoStorIOSQLitePutResolver())
                        .getResolver(new DBProfileInfoStorIOSQLiteGetResolver())
                        .deleteResolver(new DBProfileInfoStorIOSQLiteDeleteResolver()).build())
                .addTypeMapping(DBProfileInnerInfo.class, SQLiteTypeMapping.<DBProfileInnerInfo>builder()
                        .putResolver(new DBProfileInnerInfoStorIOSQLitePutResolver())
                        .getResolver(new DBProfileInnerInfoStorIOSQLiteGetResolver())
                        .deleteResolver(new DBProfileInnerInfoStorIOSQLiteDeleteResolver()).build())
                .addTypeMapping(ProfileUser.class, SQLiteTypeMapping.<ProfileUser>builder()
                        .putResolver(new ProfilePutResolver())
                        .getResolver(new ProfileGetResolver())
                        .deleteResolver(new ProfileDeleteResolver()).build())
                .build();
    }*/
}
