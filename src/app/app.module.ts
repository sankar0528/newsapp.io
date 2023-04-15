/* MODULES IMPORTS */
import { BrowserModule } from '@angular/platform-browser';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';


/* COMPONENTS IMPORTS */
import { AppComponent } from './app.component';
import { BookmarksPageComponent } from './routes/bookmarks-page/bookmarks-page.component';
import { ConnectedPageComponent } from './routes/connected-page/connected-page.component';
import { HeaderComponent } from './shared/header/header.component';
import { HomePageComponent } from './routes/home-page/home-page.component';
import { LoginFormComponent } from './shared/forms/login-form/login-form.component';
import { NewsArticleComponent } from './shared/news-article/news-article.component';
import { NewsSourceComponent } from './shared/news-source/news-source/news-source.component';
import { NewsSourceSelectorComponent } from './shared/forms/news-source-selector/news-source-selector.component';
import { RegisterFormComponent } from './shared/forms/register-form/register-form.component';
import { SingleBookmarkComponent } from './shared/single-bookmark/single-bookmark.component';


/* ROUTER IMPORT */
import { AppRouterModule } from './app.router';


/* SERVICES IMPORTS */
import { CrudService } from './services/crud/crud.service';


/* DEFINITION & EXPORT */
@NgModule({
    declarations: [
        AppComponent,
        BookmarksPageComponent,
        ConnectedPageComponent,
        HeaderComponent,
        HomePageComponent,
        NewsArticleComponent,
        LoginFormComponent,
        NewsSourceComponent,
        NewsSourceSelectorComponent,
        RegisterFormComponent,
        SingleBookmarkComponent
    ],
    imports: [
        BrowserModule,
        CommonModule,
        FontAwesomeModule,
        FormsModule,
        HttpClientModule,
        ReactiveFormsModule,
        RouterModule.forRoot(AppRouterModule, { onSameUrlNavigation: 'reload' })
    ],
    providers: [CrudService],
    bootstrap: [AppComponent]
})
export class AppModule { }
