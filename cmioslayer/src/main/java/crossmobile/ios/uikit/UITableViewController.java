/* (c) 2019 by Panayotis Katsaloulis
 *
 * CrossMobile is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 2.
 *
 * CrossMobile is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with CrossMobile; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package crossmobile.ios.uikit;

import crossmobile.ios.foundation.NSIndexPath;
import org.crossmobile.bridge.ann.CMClass;
import org.crossmobile.bridge.ann.CMConstructor;
import org.crossmobile.bridge.ann.CMGetter;
import org.crossmobile.bridge.ann.CMSetter;

import java.util.ArrayList;
import java.util.List;

/**
 * UITableViewController class defines a controller object that handles table
 * views.
 */
@CMClass
public class UITableViewController extends UIViewController implements UITableViewDataSource, UITableViewDelegate {

    private final int style;
    private UITableView tableview;
    private List<UITableViewSection> sections;

    /**
     * The default table view controller constructor.
     */
    public UITableViewController() {
        this(UITableViewStyle.Plain);
    }

    /**
     * Constructs a table view controller with the specified style.
     *
     * @param UITableViewStyle A constant that specifies the style of table view
     *                         that the controller object is to manage (UITableViewStyle.Plain or
     *                         UITableViewStyle.Grouped).
     * @see crossmobile.ios.uikit.UITableViewStyle
     */
    @CMConstructor("- (instancetype)initWithStyle:(UITableViewStyle)style;")
    public UITableViewController(int UITableViewStyle) {
        this.style = UITableViewStyle;
    }

    /**
     * Sets the table view for this view controller.
     *
     * @param tableview The table view for this view controller.
     */
    @CMSetter("@property(nonatomic, strong) UITableView *tableView;")
    public void setTableView(UITableView tableview) {
        this.tableview = tableview;
    }

    /**
     * Returns the table view of this view controller.
     *
     * @return The table view of this view controller.
     */
    @CMGetter("@property(nonatomic, strong) UITableView *tableView;")
    public UITableView tableView() {
        if (tableview == null) {
            tableview = new UITableView();
            tableview.setDataSource(this);
            tableview.setDelegate(this);
        }
        return tableview;
    }

    @Override
    public void loadView() {
        setView(tableView());
    }

    @Override
    public String titleForFooterInSection(UITableView table, int section) {
        if (sections == null || sections.isEmpty() || sections.get(section) == null)
            return null;
        return sections.get(section).footerTitle;
    }

    @Override
    public String titleForHeaderInSection(UITableView table, int section) {
        if (sections == null || sections.isEmpty() || sections.get(section) == null)
            return null;
        return sections.get(section).headerTitle;
    }

    @Override
    public UITableViewCell cellForRowAtIndexPath(UITableView tableView, NSIndexPath idx) {
        if (sections == null || sections.isEmpty() || sections.get(idx.section()) == null || sections.get(idx.section()).cells.length < idx.row())
            return null;
        return sections.get(idx.section()).cells[idx.row()];
    }

    @Override
    public int numberOfRowsInSection(UITableView tableView, int section) {
        if (sections == null || sections.isEmpty() || sections.get(section) == null || sections.get(section).cells == null)
            return 0;
        return sections.get(section).cells.length;
    }

    void addSection(String headerTitle, String footerTitle, UITableViewCell[] cells) {
        if (sections == null)
            sections = new ArrayList<>();
        sections.add(new UITableViewSection(headerTitle, footerTitle, cells));
    }

    @Override
    public double heightForRowAtIndexPath(UITableView tableView, NSIndexPath indexPath) {
        return sections == null || sections.isEmpty() ? 0 : sections.get(indexPath.section()).cells[indexPath.row()].getRowHeight(tableView);
    }

    @Override
    public int numberOfSectionsInTableView(UITableView table) {
        return sections == null || sections.isEmpty() ? 1 : sections.size();
    }

    private class UITableViewSection {
        String headerTitle;
        String footerTitle;
        UITableViewCell[] cells;

        public UITableViewSection(String headerTitle, String footerTitle, UITableViewCell[] cells) {
            this.headerTitle = headerTitle;
            this.footerTitle = footerTitle;
            this.cells = cells == null ? new UITableViewCell[0] : cells;
        }
    }
}