package com.socrata.decima.http

import com.socrata.decima.util.JsonFormats
import org.json4s.Formats
import org.scalatra.ScalatraServlet
import org.scalatra.json.JacksonJsonSupport

/**
 * DecimaServlet serves the landing page for Decima
 */
class DecimaServlet extends ScalatraServlet with JacksonJsonSupport {

  override protected implicit def jsonFormats: Formats = JsonFormats.Formats

  get("/") {
    """
    <html>
      <head>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.8.3/underscore.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.3/handlebars.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.6/moment.js"></script>

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">

        <script src="/script/app.js"></script>
        <link rel="stylesheet" href="/style/app.css">
      </head>
  <body>
    <div class="sidebar hidden-md hidden-sm hidden-xs">
      <div class="container sidebar-container">
        <a class="header-link" href="#">
          <img class="header-img" width="201" height="100" src="/images/socrata-logo.svg">
        </a>
        <div class="legend">
          <div class="legend-item version-match">Matches RC</div>
          <div class="legend-item version-no-match">Does Not Match RC</div>
          <div class="legend-item version-na">No Data</div>
        </div>
        <form class="form-inline">
          <div class="form-group">
            <label for="service-filter" class="control-label">Services</label>
            <div class="input-group">
              <input id="service-filter" type="text" class="form-control" name="service-filter" placeholder="Filter Services">
              <div class="input-group-addon"><span class="glyphicon glyphicon-info-sign"></span></div>
            </div>
          </div>
        </form>
      </div>
    </div>
    <div class="container">
      <div class="header">
        <div class="header-text">Decima -- Deployment Tracking</div>
      </div>
      <div class="container" id="services-table-rows"></div>
    </div>
    <script id="service-template" type="text/x-handlebars-template">
      <div class="service">
        <!--<h3>{{service_alias}}</h3>-->
        <div class="service-name">{{service_alias}}</div>
        <div class="region-container">
          {{#each env_parity}}
          <div class="region {{col_class}} {{match_class}}">
            {{environment}}
          </div>
          {{/each}}
        </div>
      </div>
    </script>
  </body>
    </html>
    """
  }

  get("/version") {
    contentType = formats("json")
    buildinfo.BuildInfo.toJson
  }
}
